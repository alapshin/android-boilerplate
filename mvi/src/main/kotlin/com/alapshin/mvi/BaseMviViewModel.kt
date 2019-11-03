package com.alapshin.mvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseMviViewModel<E : MviEvent, S : MviState> : ViewModel(), MviViewModel<E, S> {
    override val state = MutableLiveData<S>()

    final override fun setState(state: S) {
        this.state.postValue(state)
    }
}
