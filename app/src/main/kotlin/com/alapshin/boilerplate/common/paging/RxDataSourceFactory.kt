package com.alapshin.boilerplate.common.paging

import androidx.paging.DataSource

abstract class RxDataSourceFactory<Key, Value> : DataSource.Factory<Key, Value>(), HasNetworkState {
    abstract fun invalidate()
}
