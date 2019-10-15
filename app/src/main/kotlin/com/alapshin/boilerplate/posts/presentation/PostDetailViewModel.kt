package com.alapshin.boilerplate.posts.presentation

import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.data.PostRepository
import com.alapshin.mvi.MviEvent
import com.alapshin.mvi.MviState
import com.alapshin.mvi.Processor
import com.alapshin.mvi.Reducer
import com.alapshin.mvi.RxMviViewModel
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(private val repository: PostRepository) :
    RxMviViewModel<PostDetailViewModel.Event, PostDetailViewModel.State>() {

    sealed class Event : MviEvent {
        data class Get(val id: Int) : Event()
    }

    data class State(
        val post: Post? = null,
        val error: Throwable? = null,
        val progress: Boolean = false
    ) : MviState

    override fun reducer(): Reducer<State> {
        return { state1, state2 -> state2 }
    }

    override fun processor(): Processor<Event, State> {
        val getTransformer = ObservableTransformer<Event.Get, State> {
            it.switchMap { event ->
                repository.getPost(event.id)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .map { State(post = it) }
                    .startWith(State(progress = true))
                    .onErrorReturn { State(error = it) }
            }
        }

        return { events -> events.ofType(Event.Get::class.java).compose(getTransformer) }
    }
}
