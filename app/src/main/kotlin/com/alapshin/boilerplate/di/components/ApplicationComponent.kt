package com.alapshin.boilerplate.di.components

import android.app.Application
import android.content.Context
import com.alapshin.boilerplate.BoilerplateApplication
import com.alapshin.boilerplate.di.modules.MainActivityModule
import com.alapshin.boilerplate.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MainActivityModule::class,

    NetworkModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun context(context: Context): Builder
        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(application: BoilerplateApplication)
}