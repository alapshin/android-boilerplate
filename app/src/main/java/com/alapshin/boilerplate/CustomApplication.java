package com.alapshin.boilerplate;

import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import androidx.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;

public class CustomApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        if (BuildConfig.DEBUG) {
            setupLeakCanary();
            setupStrictMode();
            // Workaround for https://issuetracker.google.com/issues/36951662
            new Handler().postAtFrontOfQueue(this::setupStrictMode);
        }
    }

    protected void setupLeakCanary() {
        LeakCanary.install(this);
    }

    protected void setupStrictMode() {
        StrictMode.VmPolicy.Builder vmBuilder = new StrictMode.VmPolicy.Builder()
                .penaltyLog()
                .penaltyDeath()
                .detectActivityLeaks()
                .detectFileUriExposure()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectLeakedSqlLiteObjects();
        if (Build.VERSION.SDK_INT >= 23) {
            // Disabled due to 3rd party libraries
            // vmBuilder.detectCleartextNetwork();
            if (Build.VERSION.SDK_INT >= 26) {
                vmBuilder.detectContentUriWithoutPermission();
            }
        }
        StrictMode.setVmPolicy(vmBuilder.build());

        StrictMode.ThreadPolicy.Builder threadBuilder = new StrictMode.ThreadPolicy.Builder()
                .penaltyLog()
                .penaltyDeath()
                .detectNetwork()
                // Disabled due to ResourceCompat#getFont()
                //.detectDiskReads()
                //.detectDiskWrites()
                .detectCustomSlowCalls();
        if (Build.VERSION.SDK_INT >= 23) {
            threadBuilder.detectResourceMismatches();
            if (Build.VERSION.SDK_INT >= 26) {
                threadBuilder.detectUnbufferedIo();
            }
        }
        StrictMode.setThreadPolicy(threadBuilder.build());
    }
}
