package com.alapshin.boilerplate.log

import timber.log.Timber

/**
 * Wrapper around [Timber]
 */
object LogUtil {
    fun v(msg: String) {
        Timber.v(msg)
    }

    fun v(tr: Throwable) {
        Timber.v(tr)
    }

    fun v(msg: String, tr: Throwable) {
        Timber.v(tr, msg)
    }

    fun d(msg: String) {
        Timber.d(msg)
    }

    fun d(tr: Throwable) {
        Timber.d(tr)
    }

    fun d(msg: String, tr: Throwable) {
        Timber.d(tr, msg)
    }

    fun i(msg: String) {
        Timber.i(msg)
    }

    fun i(tr: Throwable) {
        Timber.i(tr)
    }

    fun i(msg: String, tr: Throwable) {
        Timber.i(tr, msg)
    }

    fun w(msg: String) {
        Timber.w(msg)
    }

    fun w(tr: Throwable) {
        Timber.w(tr)
    }

    fun w(msg: String, tr: Throwable) {
        Timber.w(tr, msg)
    }

    fun e(msg: String) {
        Timber.e(msg)
    }

    fun e(tr: Throwable) {
        Timber.e(tr)
    }

    fun e(msg: String, tr: Throwable) {
        Timber.e(tr, msg)
    }
}
