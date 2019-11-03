package com.alapshin.mvi

import androidx.lifecycle.LiveData

interface MviViewModel<E : MviEvent, S : MviState> {
    val state: LiveData<S>
    fun dispatch(event: E)
    fun setState(state: S)
}
