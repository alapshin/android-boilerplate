package com.alapshin.boilerplate.common.paging

import androidx.paging.PageKeyedDataSource
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class RxPageKeyedDataSource<Key, Value, Response>(private val disposables: CompositeDisposable) :
    PageKeyedDataSource<Key, Value>(), RxDataSource {
    override val networkState = BehaviorRelay.create<NetworkState>()

    protected data class CallbackArgs<Key, Value>(
        val data: List<Value>,
        val adjacentPageKey: Key?
    )

    protected class InitialCallbackArgs<Key, Value>(
        val data: List<Value>,
        val position: Int?,
        val totalCount: Int?,
        val nextPageKey: Key?,
        val previousPageKey: Key?
    )

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        networkState.accept(NetworkState.Loading())
        disposables.add(createResponse(params)
            .map { parseResponse(params, it) }.subscribe(
                { result ->
                    callback.onResult(
                        result.data,
                        result.adjacentPageKey
                    )
                    networkState.accept(NetworkState.Success())
                },
            { error -> networkState.accept(NetworkState.Error(error)) }
        ))
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
    }

    override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Key, Value>) {
        networkState.accept(NetworkState.Loading())
        disposables.add(createInitialResponse(params)
            .map { parseInitialResponse(params, it) }
            .subscribe(
                { result ->
                    callback.onResult(
                        result.data,
                        result.previousPageKey,
                        result.nextPageKey
                    )
                    networkState.accept(NetworkState.Success())
                },
                { error -> networkState.accept(NetworkState.Error(error)) }
        ))
    }

    /**
     * Create [Single] for loading data portion
     */
    protected abstract fun createResponse(params: LoadParams<Key>): Single<Response>
    /**
     * Calculate data for callback using loading parameters and received response
     */
    protected abstract fun parseResponse(params: LoadParams<Key>, response: Response): CallbackArgs<Key, Value>
    /**
     * Create [Single] for loading initial data portion
     */
    protected abstract fun createInitialResponse(params: LoadInitialParams<Key>): Single<Response>

    /**
     * Calculate data for callback using initial parameters and received response
     */
    protected abstract fun parseInitialResponse(params: LoadInitialParams<Key>, response: Response):
        InitialCallbackArgs<Key, Value>
}
