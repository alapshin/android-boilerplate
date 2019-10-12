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

class PostDetailViewModel @Inject constructor(repository: PostRepository) :
    RxMviViewModel<PostDetailViewModel.Event, PostDetailViewModel.State>(createProcessor(repository), reducer) {

    sealed class Event : MviEvent {
        data class Get(val id: Int) : Event()
    }

    data class State(
        val post: Post? = null,
        val error: Throwable? = null,
        val progress: Boolean = false
    ) : MviState

    companion object {
        val reducer: Reducer<State> = { state1, state2 -> state2 }

        fun createProcessor(repository: PostRepository): Processor<Event, State> {
            val getTransformer = ObservableTransformer<Event.Get, State> {
                it.switchMap { event ->
                    repository.getPost(event.id)
                        .subscribeOn(Schedulers.io())
                        .map { State(post = it) }
                        .startWith(State(progress = true))
                        .onErrorReturn { State(error = it) }
                }
            }

            return { events -> events.ofType(Event.Get::class.java).compose(getTransformer) }
        }
    }
}
