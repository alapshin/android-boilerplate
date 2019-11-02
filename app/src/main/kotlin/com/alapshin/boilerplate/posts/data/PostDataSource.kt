package com.alapshin.boilerplate.posts.data

import androidx.paging.PagedList
import com.alapshin.boilerplate.common.paging.RxPageKeyedDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PostDataSource constructor(disposables: CompositeDisposable, private val repository: PostRepository) :
    RxPageKeyedDataSource<Int, Post, List<Post>>(disposables) {

    companion object {
        val CONFIG = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
    }

    override fun createResponse(params: LoadParams<Int>): Single<List<Post>> {
        return repository.getPosts(params.key)
    }

    override fun parseResponse(params: LoadParams<Int>, response: List<Post>): CallbackArgs<Int, Post> {
        val adjacentPageKey = if (response.isEmpty()) null else params.key + 1
        return CallbackArgs(response, adjacentPageKey = adjacentPageKey)
    }

    override fun createInitialResponse(params: LoadInitialParams<Int>): Single<List<Post>> {
        return repository.getPosts(1)
    }

    override fun parseInitialResponse(params: LoadInitialParams<Int>, response: List<Post>):
        InitialCallbackArgs<Int, Post> {
        val nextPageKey = if (response.isEmpty()) null else 2
        return InitialCallbackArgs(response, null, null, previousPageKey = null, nextPageKey = nextPageKey)
    }
}
