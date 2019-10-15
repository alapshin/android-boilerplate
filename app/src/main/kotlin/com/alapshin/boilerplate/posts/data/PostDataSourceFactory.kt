package com.alapshin.boilerplate.posts.data

import androidx.paging.DataSource
import com.alapshin.boilerplate.common.paging.RxDataSourceFactory

class PostDataSourceFactory constructor(private val repository: PostRepository) : RxDataSourceFactory<Int, Post>() {
    override fun create(): DataSource<Int, Post> {
        return PostDataSource(repository).also { source ->
            source.networkState.subscribe(networkState)
        }
    }
}
