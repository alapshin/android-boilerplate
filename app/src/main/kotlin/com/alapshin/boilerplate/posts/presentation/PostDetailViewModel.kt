package com.alapshin.boilerplate.posts.presentation

import androidx.lifecycle.SavedStateHandle
import com.alapshin.boilerplate.posts.data.Post
import com.alapshin.boilerplate.posts.data.PostRepository
import com.happify.mvi.core.MviEvent
import com.happify.mvi.core.MviState
import com.happify.mvi.rx.Processor
import com.happify.mvi.rx.Reducer
import com.happify.mvi.rx.RxMviViewModel
import com.squareup.inject.assisted.Assisted
import com.vikingsen.inject.viewmodel.ViewModelInject
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

class PostDetailViewModel @ViewModelInject constructor(
    private val repository: PostRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : RxMviViewModel<PostDetailViewModel.State>() {

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

    override fun processor(): Processor<State> {
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
