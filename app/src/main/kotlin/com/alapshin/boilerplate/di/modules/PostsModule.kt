package com.alapshin.boilerplate.di.modules

import androidx.lifecycle.ViewModel
import com.alapshin.boilerplate.di.ViewModelKey
import com.alapshin.boilerplate.posts.data.PostDataSourceFactory
import com.alapshin.boilerplate.posts.data.PostRepository
import com.alapshin.boilerplate.posts.data.PostRepositoryImpl
import com.alapshin.boilerplate.posts.presentation.PostDetailViewModel
import com.alapshin.boilerplate.posts.presentation.PostListViewModel
import com.alapshin.boilerplate.posts.view.PostDetailFragment
import com.alapshin.boilerplate.posts.view.PostListFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PostsModule {
    @ContributesAndroidInjector
    abstract fun contributePostListFragment(): PostListFragment

    @ContributesAndroidInjector
    abstract fun contributePostDetailFragment(): PostDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    abstract fun bindPostDetailViewModel(viewModel: PostDetailViewModel): ViewModel

    @Binds
    abstract fun bindPostRepository(repository: PostRepositoryImpl): PostRepository

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providePostDataSourceFactory(repository: PostRepository): PostDataSourceFactory {
            return PostDataSourceFactory(repository)
        }
    }
}
