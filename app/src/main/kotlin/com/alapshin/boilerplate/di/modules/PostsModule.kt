package com.alapshin.boilerplate.di.modules

import androidx.lifecycle.ViewModel
import com.alapshin.boilerplate.di.ViewModelKey
import com.alapshin.boilerplate.posts.data.PostRepository
import com.alapshin.boilerplate.posts.data.PostRepositoryImpl
import com.alapshin.boilerplate.posts.presentation.PostDetailViewModel
import com.alapshin.boilerplate.posts.presentation.PostListViewModel
import com.alapshin.boilerplate.posts.view.PostDetailFragment
import com.alapshin.boilerplate.posts.view.PostListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface PostsModule {
    @ContributesAndroidInjector
    fun contributePostListFragment(): PostListFragment

    @ContributesAndroidInjector
    fun contributePostDetailFragment(): PostDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    fun bindPostListViewModel(viewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    fun bindPostDetailViewModel(viewModel: PostDetailViewModel): ViewModel

    @Binds
    fun bindPostRepository(repository: PostRepositoryImpl): PostRepository
}
