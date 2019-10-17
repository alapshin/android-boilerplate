package com.alapshin.boilerplate.common.paging

import androidx.paging.DataSource
import io.reactivex.Observable

/**
 * [DataSource] implementation that exposes status of network request via [Observable]
 */
interface RxDataSource {
    val networkState: Observable<NetworkState>
}
