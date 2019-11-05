package com.alapshin.mvi

/**
 * Wrapper for data exposed via a LiveData that should be used only once
 *
 * See Also: https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Consumable<out T>(private val value: T) {
    private var consumed = false

    /**
     * Return true if value was consumed, otherwise false.
     */
    fun isConsumed() = consumed

    /**
     * Return the value, even if it's already been consumed.
     */
    fun peek(): T = value

    /**
     * Return the value and mark it as consumed. If value has been already consumed returns null.
     */
    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            value.also {
                consumed = true
            }
        }
    }
}
