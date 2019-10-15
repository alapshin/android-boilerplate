package com.alapshin.boilerplate.common.paging

import androidx.paging.PagedList
import io.reactivex.Observable

class Listing<T>(
    val list: Observable<PagedList<T>>,
    val networkState: Observable<NetworkState>
)
