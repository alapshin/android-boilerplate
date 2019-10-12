package com.alapshin.boilerplate.di.modules

import com.alapshin.boilerplate.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @ContributesAndroidInjector(modules = [
        PostsModule::class,
        ViewModelFactoryModule::class
    ])
    fun contributeMainActivity(): MainActivity
}
