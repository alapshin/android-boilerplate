package com.alapshin.boilerplate.common.paging

import androidx.paging.DataSource
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

abstract class RxDataSourceFactory<Key, Value> : DataSource.Factory<Key, Value>() {
    var disposables: CompositeDisposable? = null
    private val dataSource = BehaviorRelay.create<DataSource<Key, Value>>()
    private val networkState: Observable<NetworkState> = dataSource.flatMap {
        (it as RxDataSource).networkState
    }

    fun invalidate() {
        dataSource.value?.invalidate()
    }

    fun networkState(): Observable<NetworkState> = networkState

    final override fun create(): DataSource<Key, Value> {
        requireNotNull(disposables)

        return create(requireNotNull(disposables)).also {
            if (it is RxDataSource) {
                dataSource.accept(it)
            }
        }
    }

    protected abstract fun create(disposables: CompositeDisposable): DataSource<Key, Value>
}
