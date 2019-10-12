package com.alapshin.boilerplate.di.modules

import com.alapshin.boilerplate.ApiService
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [
    CacheModule::class,
    InterceptorModule::class
])
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpClient(
        cache: Cache,
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .callTimeout(30, TimeUnit.SECONDS).apply {
                for (interceptor in interceptors) {
                    addInterceptor(interceptor)
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
    fun provideRetrofit(
        httpClient: OkHttpClient,
        converterFactories: Set<@JvmSuppressWildcards Converter.Factory>,
        callAdapterFactories: Set<@JvmSuppressWildcards CallAdapter.Factory>
    ): Retrofit {
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

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofitService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
