package com.happify.mvi.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MviViewModel<S : MviState> : ViewModel() {
    fun state(): LiveData<S> {
        return state
    }

    open fun start() {
    }

    abstract fun process(event: MviEvent)

    protected val state = object : MutableLiveData<S>() {
        private var activated = false

        // When state is subscribed for the first time call start method to initialize ViewModel.
        override fun onActive() {
            super.onActive()
            if (!activated) {
                start()
                activated = true
            }
        }
    }
}
