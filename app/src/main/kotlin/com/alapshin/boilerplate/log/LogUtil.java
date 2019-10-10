package com.alapshin.boilerplate.log;

import timber.log.Timber;

/**
 * Wrapper around {@link Timber}
 *
 * In debug builds logs are written to Logcat.
 * In release builds logs are written to Crashlytics.
 */
public class LogUtil {
    public static void v(String msg) {
        Timber.v(msg);
    }

    public static void v(Throwable tr) {
        Timber.v(tr);
    }

    public static void v(String msg, Throwable tr) {
        Timber.v(tr, msg);
    }

    public static void d(String msg) {
        Timber.d(msg);
    }

    public static void d(Throwable tr) {
        Timber.d(tr);
    }

    public static void d(String msg, Throwable tr) {
        Timber.d(tr, msg);
    }

    public static void i(String msg) {
        Timber.i(msg);
    }

    public static void i(Throwable tr) {
        Timber.i(tr);
    }

    public static void i(String msg, Throwable tr) {
        Timber.i(tr, msg);
    }

    public static void w(String msg) {
        Timber.w(msg);
    }

    public static void w(Throwable tr) {
        Timber.w(tr);
    }

    public static void w(String msg, Throwable tr) {
        Timber.w(tr, msg);
    }

    public static void e(String msg) {
        Timber.e(msg);
    }

    public static void e(Throwable tr) {
        Timber.e(tr);
    }

    public static void e(String msg, Throwable tr) {
        Timber.e(tr, msg);
    }
}
