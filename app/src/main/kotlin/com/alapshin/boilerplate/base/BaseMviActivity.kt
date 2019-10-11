package com.alapshin.boilerplate.base

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseMviActivity<T: ViewBinding, S: MviState> : BaseActivity<T>(), MviView<S>, HasAndroidInjector  {
    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory
    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}