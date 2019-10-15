package com.alapshin.boilerplate.common.paging

import io.reactivex.Observable

interface HasNetworkState {
    val networkState: Observable<NetworkState>
}
