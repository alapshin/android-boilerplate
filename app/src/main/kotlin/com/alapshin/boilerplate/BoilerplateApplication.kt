package com.alapshin.boilerplate

import android.os.Build
import android.os.Handler
import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.alapshin.boilerplate.di.AppInjector

import com.crashlytics.android.Crashlytics
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

import io.fabric.sdk.android.Fabric
import dagger.android.DispatchingAndroidInjector
import com.alapshin.boilerplate.di.components.DaggerApplicationComponent
import com.alapshin.boilerplate.log.CrashlyticsTree
import com.alapshin.boilerplate.log.LogUtil
import com.alapshin.boilerplate.log.LogcatTree
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

class BoilerplateApplication : MultiDexApplication(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        setupDagger()
        setupLogging()
        setupRxJava2ErrorHandler()

        Fabric.with(this, Crashlytics())
        if (BuildConfig.DEBUG) {
            setupStrictMode()
            // Workaround for https://issuetracker.google.com/issues/36951662
            Handler().postAtFrontOfQueue { this.setupStrictMode() }
        }
    }

    private fun setupDagger() {
        AppInjector.init(this)
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(LogcatTree(LogUtil::class.java.name))
        } else {
            Timber.plant(CrashlyticsTree());
        }
    }

    private fun setupStrictMode() {
        val vmBuilder = StrictMode.VmPolicy.Builder()
                .penaltyLog()
                .penaltyDeath()
                .detectActivityLeaks()
                .detectFileUriExposure()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectLeakedSqlLiteObjects()
        if (Build.VERSION.SDK_INT >= 23) {
            // Disabled due to 3rd party libraries
            vmBuilder.detectCleartextNetwork()
        }
        if (Build.VERSION.SDK_INT >= 26) {
            vmBuilder.detectContentUriWithoutPermission()
        }
        StrictMode.setVmPolicy(vmBuilder.build())

        val threadBuilder = StrictMode.ThreadPolicy.Builder()
                .penaltyLog()
                .penaltyDeath()
                .detectNetwork()
                // Disabled due to ResourceCompat#getFont()
                //.detectDiskReads()
                //.detectDiskWrites()
                .detectCustomSlowCalls()
        if (Build.VERSION.SDK_INT >= 23) {
            threadBuilder.detectResourceMismatches()
        }
        if (Build.VERSION.SDK_INT >= 26) {
            threadBuilder.detectUnbufferedIo()
        }
        StrictMode.setThreadPolicy(threadBuilder.build())
    }

    // https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling
    private fun setupRxJava2ErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            if (e is IOException || e is SocketException) {
                // Fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (e is InterruptedException) {
                // Fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (e is NullPointerException || e is IllegalArgumentException) {
                // That's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler!!
                    .uncaughtException(Thread.currentThread(), e)
                return@setErrorHandler
            }
            if (e is IllegalStateException) {
                // That's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler!!
                    .uncaughtException(Thread.currentThread(), e)
                return@setErrorHandler
            }
            val ex = if (e is UndeliverableException) e.cause else e
            LogUtil.e("Undeliverable exception received, not sure what to do", ex)
        }
    }

}
