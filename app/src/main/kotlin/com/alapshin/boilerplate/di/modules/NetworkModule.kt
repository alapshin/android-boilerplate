package com.alapshin.boilerplate.di.modules

import com.alapshin.boilerplate.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.TreeSet
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpClient(cache: Cache,
                          interceptors: Map<Int, Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .callTimeout(30, TimeUnit.SECONDS)
            .apply {
                val keys = TreeSet(interceptors.keys)
                for (key in keys) {
                    addInterceptor(interceptors[key]!!)
                }
            }
            .build()
    }

    @Provides
    @IntoSet
    @Singleton
    @JvmStatic
    fun provideMoshiConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @IntoSet
    @Singleton
    @JvmStatic
    fun provideRxJava2AdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(httpClient: OkHttpClient,
                        converterFactories: Set<Converter.Factory>,
                        callAdapterFactories: Set<CallAdapter.Factory>): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpClient)
            .validateEagerly(true)
            .apply {
                for (factory in converterFactories) {
                    addConverterFactory(factory)
                }
                for (factory in callAdapterFactories) {
                    addCallAdapterFactory(factory)
                }
            }
            .build()
    }
}