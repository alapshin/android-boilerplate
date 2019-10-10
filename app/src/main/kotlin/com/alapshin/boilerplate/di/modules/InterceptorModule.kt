package com.alapshin.boilerplate.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import okhttp3.Interceptor

@Module
object InterceptorModule {
    @Provides
    @ElementsIntoSet
    @JvmStatic
    fun provideInterceptors(): Set<Interceptor> = setOf()
}