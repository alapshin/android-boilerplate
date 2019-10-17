package com.alapshin.boilerplate.posts.data

import androidx.paging.DataSource
import com.alapshin.boilerplate.common.paging.RxDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class PostDataSourceFactory constructor(private val repository: PostRepository) : RxDataSourceFactory<Int, Post>() {
    override fun create(disposables: CompositeDisposable): DataSource<Int, Post> {
        return PostDataSource(disposables, repository)
    }
}
