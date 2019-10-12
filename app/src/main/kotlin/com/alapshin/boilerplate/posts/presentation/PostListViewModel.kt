package com.alapshin.boilerplate.posts.presentation

import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.data.PostRepository
import com.alapshin.mvi.MviEvent
import com.alapshin.mvi.MviState
import com.alapshin.mvi.Processor
import com.alapshin.mvi.Reducer
import com.alapshin.mvi.RxMviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PostListViewModel @Inject constructor(repository: PostRepository) :
    RxMviViewModel<PostListViewModel.Event, PostListViewModel.State>(createProcessor(repository), reducer) {

    sealed class Event : MviEvent {
        class Idle : Event()
        class Redirect : Event()
    }

    data class State(
        val error: Throwable? = null,
        val progress: Boolean = false,
        val posts: List<Post>? = null
    ) : MviState

    companion object {
        val reducer: Reducer<State> = { state1, state2 ->
            if (state1.posts == null) {
                state2
            } else {
                state2.copy(posts = state1.posts)
            }
        }

        fun createProcessor(repository: PostRepository): Processor<Event, State> {
            val idleTransformer = ObservableTransformer<Event.Idle, State> {
                it.switchMap { event ->
                    repository.getPosts()
                        .delay(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .map { State(posts = it) }
                        .startWith(State(progress = true))
                        .onErrorReturn { State(error = it) }
                }
            }

            val redirectTransformer = ObservableTransformer<Event.Redirect, State> {
                it.switchMap { event -> Observable.just(State()) }
            }

            return { events -> Observable.merge(
                events.ofType(Event.Idle::class.java).compose(idleTransformer),
                events.ofType(Event.Redirect::class.java).compose(redirectTransformer)
            ) }
        }
    }
}
