package com.alapshin.boilerplate.base

import androidx.lifecycle.ViewModelProvider
import com.alapshin.boilerplate.di.Injectable
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import javax.inject.Inject

abstract class BaseMviFragment<S : MviState> : BaseFragment(), Injectable, MviView<S> {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
}
