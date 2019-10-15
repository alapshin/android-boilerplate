package com.alapshin.boilerplate.common.paging

import androidx.paging.DataSource
import com.jakewharton.rxrelay2.BehaviorRelay

abstract class RxDataSourceFactory<Key, Value> : DataSource.Factory<Key, Value>(), HasNetworkState {
    override val networkState = BehaviorRelay.create<NetworkState>()
}
