package com.alapshin.boilerplate.base

import com.happify.mvi.core.MviState
import com.happify.mvi.core.MviView
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import javax.inject.Inject

abstract class BaseMviActivity<S : MviState> : InjectableActivity(), MviView<S> {
    @Inject
    protected lateinit var vmFactory: SavedStateViewModelFactory.Factory
}
