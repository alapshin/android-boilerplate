package com.happify.mvi.core

interface MviView<S : MviState> {
    fun render(state: S)
}
