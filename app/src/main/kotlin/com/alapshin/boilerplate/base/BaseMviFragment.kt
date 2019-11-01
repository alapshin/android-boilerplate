package com.alapshin.boilerplate.base

import androidx.viewbinding.ViewBinding
import com.alapshin.boilerplate.di.Injectable
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import javax.inject.Inject

abstract class BaseMviFragment<VB : ViewBinding, S : MviState> : BaseFragment<VB>(), MviView<S>, Injectable {
    @Inject
    protected lateinit var vmFactory: SavedStateViewModelFactory.Factory
}
