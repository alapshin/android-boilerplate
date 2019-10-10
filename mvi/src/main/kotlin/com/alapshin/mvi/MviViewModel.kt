package com.alapshin.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class MviViewModel<E : MviEvent, S : MviState> : ViewModel() {
    abstract val state: LiveData<S>
    abstract fun dispatch(event: E)
}
