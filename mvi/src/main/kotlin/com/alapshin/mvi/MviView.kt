package com.alapshin.mvi

interface MviView<in S : MviState> {
    fun render(state: S)
}
