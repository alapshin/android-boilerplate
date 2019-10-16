package com.alapshin.boilerplate.posts.data

import androidx.paging.DataSource
import com.alapshin.boilerplate.common.paging.NetworkState
import com.alapshin.boilerplate.common.paging.RxDataSourceFactory
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class PostDataSourceFactory constructor(private val repository: PostRepository) : RxDataSourceFactory<Int, Post>() {
    private val dataSource = BehaviorRelay.create<PostDataSource>()
    override val networkState: Observable<NetworkState> = dataSource.flatMap { it.networkState }

    override fun invalidate() {
        dataSource.value?.invalidate()
    }

    override fun create(): DataSource<Int, Post> {
        return PostDataSource(repository).also { source ->
            dataSource.accept(source)
        }
    }
}
