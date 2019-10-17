package com.alapshin.boilerplate.posts.data

import androidx.paging.PagedList
import com.alapshin.boilerplate.common.paging.RxPageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable

class PostDataSource constructor(disposables: CompositeDisposable, private val repository: PostRepository) :
    RxPageKeyedDataSource<Int, Post>(disposables) {
    companion object {
        val CONFIG = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
    }

    override fun createLoad(params: LoadParams<Int>): Load<Int, Post> {
        return Load(repository.getPosts(params.key), params.key + 1)
    }

    override fun createInitialLoad(params: LoadInitialParams<Int>): InitialLoad<Int, Post> {
        return InitialLoad(repository.getPosts(1), null, null, previousPageKey = null, nextPageKey = 2)
    }
}
