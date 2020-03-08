package com.alapshin.boilerplate.posts.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagedList
import com.alapshin.boilerplate.common.paging.ListingFactory
import com.alapshin.boilerplate.common.paging.NetworkState
import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.data.PostDataSource
import com.alapshin.boilerplate.posts.data.PostDataSourceFactory
import com.happify.mvi.core.MviEvent
import com.happify.mvi.core.MviState
import com.happify.mvi.rx.Processor
import com.happify.mvi.rx.Reducer
import com.happify.mvi.rx.RxMviViewModel
import com.squareup.inject.assisted.Assisted
import com.vikingsen.inject.viewmodel.ViewModelInject
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class PostListViewModel @ViewModelInject constructor(
    private val dataSourceFactory: PostDataSourceFactory,
    @Assisted private val savedStateHandle: SavedStateHandle
) : RxMviViewModel<PostListViewModel.State>() {

    sealed class Event : MviEvent {
        class Idle : Event()
        class Refresh : Event()
    }

    data class State(
        val error: Throwable? = null,
        val progress: Boolean = false,
        val posts: PagedList<Post>? = null
    ) : MviState

    override fun process(event: MviEvent) {
        if (event is Event.Idle) {
            super.process(event)
        } else {
            dataSourceFactory.invalidate()
        }
    }

    override fun reducer(): Reducer<State> {
        return { state1, state2 -> state2 }
    }

    override fun processor(): Processor<State> {
        dataSourceFactory.disposables = disposables
        val listing = ListingFactory.createListing(
            PostDataSource.CONFIG,
            Schedulers.io(),
            AndroidSchedulers.mainThread(),
            dataSourceFactory
        )
        val idleTransformer = ObservableTransformer<MviEvent.Default, State> {
            it.switchMap { event ->
                Observable.combineLatest(listing.list, listing.networkState,
                    BiFunction<PagedList<Post>, NetworkState, State>
                    { list, networkState ->
                        State(progress = networkState is NetworkState.Loading, posts = list)
                    })
            }
        }

        return { events -> events.ofType(MviEvent.Default::class.java).compose(idleTransformer) }
    }
}
