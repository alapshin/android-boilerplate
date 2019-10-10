package com.alapshin.mvi

/**
 * Wrapper for data exposed via a LiveData that should be used only once
 *
 * See Also: https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Consumable<out T>(private val value: T) {
    private var consumed = false

    /**
     * Returns the value, even if it's already been consumed.
     */
    fun peekValue(): T = value

    /**
     * Returns the value and prevents its use again.
     */
    fun getUnconsumed(): T? {
        return if (consumed) {
            null
        } else {
            value.also {
                consumed = true
            }
        }
    }
}
