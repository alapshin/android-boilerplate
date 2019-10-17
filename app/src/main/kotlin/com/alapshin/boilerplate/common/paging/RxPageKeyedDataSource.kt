package com.alapshin.boilerplate.common.paging

import androidx.paging.PageKeyedDataSource
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class RxPageKeyedDataSource<Key, Value>(private val disposables: CompositeDisposable) :
    PageKeyedDataSource<Key, Value>(), RxDataSource {
    override val networkState = BehaviorRelay.create<NetworkState>()

    data class Load<Key, Value>(
        val data: Single<List<Value>>,
        val adjacentPageKey: Key?
    )

    class InitialLoad<Key, Value>(
        val data: Single<List<Value>>,
        val position: Int?,
        val totalCount: Int?,
        val previousPageKey: Key?,
        val nextPageKey: Key?
    )

    abstract fun createLoad(params: LoadParams<Key>): Load<Key, Value>
    abstract fun createInitialLoad(params: LoadInitialParams<Key>): InitialLoad<Key, Value>

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        val load = createLoad(params)
        networkState.accept(NetworkState.Loading())
        disposables.add(load.data.subscribe(
            { data ->
                networkState.accept(NetworkState.Success())
                callback.onResult(data, load.adjacentPageKey)
            },
            { error -> networkState.accept(NetworkState.Error(error)) }
        ))
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
    }

    override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Key, Value>) {
        val load = createInitialLoad(params)
        networkState.accept(NetworkState.Loading())
        disposables.add(load.data.subscribe(
            { data ->
                networkState.accept(NetworkState.Success())
                callback.onResult(data, load.previousPageKey, load.nextPageKey)
            },
            { error -> networkState.accept(NetworkState.Error(error)) }
        ))
    }
}
