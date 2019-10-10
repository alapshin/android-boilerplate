package com.alapshin.boilerplate.di.modules

import androidx.lifecycle.ViewModelProvider
import com.alapshin.boilerplate.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
