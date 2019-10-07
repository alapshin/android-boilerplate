package com.alapshin.boilerplate.di.modules

import android.content.Context

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import javax.inject.Singleton

/**
 * Provides cache for [okhttp3.OkHttpClient]
 */
@Module
object CacheModule {
    private const val CACHE_SIZE = 512 * 1024 * 1024 // 512 MB

    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpCache(context: Context): Cache {
        return Cache(context.externalCacheDir ?: context.cacheDir, CACHE_SIZE.toLong())
    }
}
