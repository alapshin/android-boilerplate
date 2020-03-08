package com.alapshin.boilerplate.base

import com.happify.mvi.core.MviState
import com.happify.mvi.core.MviView
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import javax.inject.Inject

abstract class BaseMviFragment<S : MviState> : InjectableFragment(), MviView<S> {
    @Inject
    protected lateinit var vmFactory: SavedStateViewModelFactory.Factory
}
