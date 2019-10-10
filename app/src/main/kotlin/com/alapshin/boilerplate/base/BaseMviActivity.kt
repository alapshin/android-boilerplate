package com.alapshin.boilerplate.base

import androidx.lifecycle.ViewModelProvider
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseMviActivity<S: MviState> : BaseActivity(), HasAndroidInjector, MviView<S> {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}