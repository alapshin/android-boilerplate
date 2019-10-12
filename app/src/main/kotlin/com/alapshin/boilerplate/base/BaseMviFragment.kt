package com.alapshin.boilerplate.base

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alapshin.boilerplate.di.Injectable
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import javax.inject.Inject

abstract class BaseMviFragment<T : ViewBinding, S : MviState> : BaseFragment<T>(), MviView<S>, Injectable {
    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory
}
