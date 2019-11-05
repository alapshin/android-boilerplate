package com.alapshin.boilerplate.base

import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import javax.inject.Inject

abstract class BaseMviActivity<S : MviState> : InjectableActivity(), MviView<S> {
    @Inject
    protected lateinit var vmFactory: SavedStateViewModelFactory.Factory
}
