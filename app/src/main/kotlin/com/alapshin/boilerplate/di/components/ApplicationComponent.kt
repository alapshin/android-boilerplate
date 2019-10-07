package com.alapshin.boilerplate.di.components

import com.alapshin.boilerplate.BoilerplateApplication
import com.alapshin.boilerplate.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    NetworkModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent {
    fun inject(application: BoilerplateApplication)
}