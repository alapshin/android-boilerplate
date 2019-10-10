package com.alapshin.boilerplate.log

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * A [Timber.Tree] that logs to Crashlytics
 *
 * @see [Crashlytics.log]
 * @see [Crashlytics.logException]
 * @see <a href="https://docs.fabric.io/android/crashlytics/enhanced-reports.html#custom-logging">Fabric docs</a>
 */
class CrashlyticsTree : Timber.Tree() {
    companion object {
        private const val KEY_TAG = "tag"
        private const val KEY_MESSAGE = "message"
        private const val KEY_PRIORITY = "priority"
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.ERROR
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Crashlytics.setInt(KEY_PRIORITY, priority)
        Crashlytics.setString(KEY_TAG, tag)
        Crashlytics.setString(KEY_MESSAGE, message)

        if (t == null) {
            Crashlytics.log(message)
        } else {
            Crashlytics.logException(t)
        }
    }
}
