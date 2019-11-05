package com.alapshin.boilerplate.base

import androidx.appcompat.app.AppCompatActivity
import com.alapshin.mvi.MviState
import com.alapshin.mvi.MviView
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseMviActivity<S : MviState> : AppCompatActivity(), MviView<S>, HasAndroidInjector {
    @Inject
    protected lateinit var vmFactory: SavedStateViewModelFactory.Factory
    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
