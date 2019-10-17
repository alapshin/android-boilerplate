package com.alapshin.boilerplate.posts.presentation

import androidx.paging.PagedList
import com.alapshin.boilerplate.common.paging.ListingFactory
import com.alapshin.boilerplate.common.paging.NetworkState
import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.data.PostDataSource
import com.alapshin.boilerplate.posts.data.PostDataSourceFactory
import com.alapshin.mvi.MviEvent
import com.alapshin.mvi.MviState
import com.alapshin.mvi.Processor
import com.alapshin.mvi.Reducer
import com.alapshin.mvi.RxMviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel @Inject constructor(private val dataSourceFactory: PostDataSourceFactory) :
    RxMviViewModel<PostListViewModel.Event, PostListViewModel.State>() {

    sealed class Event : MviEvent {
        class Idle : Event()
        class Refresh : Event()
    }

    data class State(
        val error: Throwable? = null,
        val progress: Boolean = false,
        val posts: PagedList<Post>? = null
    ) : MviState

    init {
        start()
    }

    override fun dispatch(event: Event) {
        if (event is Event.Idle) {
            super.dispatch(event)
        } else {
            dataSourceFactory.invalidate()
        }
    }

    override fun reducer(): Reducer<State> {
        return { state1, state2 -> state2 }
    }

    override fun processor(): Processor<Event, State> {
        dataSourceFactory.disposables = disposables
        val listing = ListingFactory.createListing(
            PostDataSource.CONFIG,
            Schedulers.io(),
            AndroidSchedulers.mainThread(),
            dataSourceFactory
        )
        val idleTransformer = ObservableTransformer<Event.Idle, State> {
            it.switchMap { event ->
                Observable.combineLatest(listing.list, listing.networkState,
                    BiFunction<PagedList<Post>, NetworkState, State>
                    { list, networkState ->
                        State(progress = networkState is NetworkState.Loading, posts = list)
                    })
            }
        }

        return { events -> events.ofType(Event.Idle::class.java).compose(idleTransformer) }
    }
}
