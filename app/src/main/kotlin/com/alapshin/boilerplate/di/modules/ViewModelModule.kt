package com.alapshin.boilerplate.di.modules

import com.vikingsen.inject.viewmodel.ViewModelModule
import dagger.Module

@ViewModelModule
@Module(includes = [ViewModelInject_ViewModelModule::class])
abstract class ViewModelModule
