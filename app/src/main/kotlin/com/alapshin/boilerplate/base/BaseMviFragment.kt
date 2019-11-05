package com.alapshin.boilerplate.base

import androidx.fragment.app.Fragment
import com.alapshin.boilerplate.di.Injectable
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import javax.inject.Inject

abstract class BaseMviFragment<S : MviState> : Fragment(), MviView<S>, Injectable {
    @Inject
    protected lateinit var vmFactory: SavedStateViewModelFactory.Factory
}
