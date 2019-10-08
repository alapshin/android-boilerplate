package com.alapshin.boilerplate

import android.os.Build
import android.os.Handler
import android.os.StrictMode
import androidx.multidex.MultiDexApplication

import com.crashlytics.android.Crashlytics
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

import io.fabric.sdk.android.Fabric
import dagger.android.DispatchingAndroidInjector
import com.alapshin.boilerplate.di.components.ApplicationComponent
import com.alapshin.boilerplate.di.components.DaggerApplicationComponent
import javax.inject.Inject

class BoilerplateApplication : MultiDexApplication(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        setupDagger()

        Fabric.with(this, Crashlytics())
        if (BuildConfig.DEBUG) {
            setupStrictMode()
            // Workaround for https://issuetracker.google.com/issues/36951662
            Handler().postAtFrontOfQueue { this.setupStrictMode() }
        }
    }

    private fun setupDagger() {
        DaggerApplicationComponent.create().inject(this)
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
}
