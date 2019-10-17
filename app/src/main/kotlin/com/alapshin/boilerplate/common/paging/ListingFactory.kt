package com.alapshin.boilerplate.common.paging

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.Scheduler

object ListingFactory {
    fun <Key, Value> createListing(
        config: PagedList.Config,
        fetchScheduler: Scheduler,
        notifyScheduler: Scheduler,
        dataSourceFactory: RxDataSourceFactory<Key, Value>
    ): Listing<Value> {

        val state = dataSourceFactory.networkState()
        val pagedList = RxPagedListBuilder(dataSourceFactory, config)
            .setFetchScheduler(fetchScheduler)
            .setNotifyScheduler(notifyScheduler)
            .buildObservable()

        return Listing(pagedList, state)
    }
}
