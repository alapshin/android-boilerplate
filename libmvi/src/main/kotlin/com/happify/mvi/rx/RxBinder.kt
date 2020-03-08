package com.happify.mvi.rx

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.happify.mvi.core.MviState
import io.reactivex.disposables.CompositeDisposable

fun <S : MviState> Fragment.bind(view: RxMviView<S>, viewModel: RxMviViewModel<S>) {
    RxBinder<S>(viewLifecycleOwner).bind(view, viewModel)
}

fun <S : MviState> ComponentActivity.bind(view: RxMviView<S>, viewModel: RxMviViewModel<S>) {
    RxBinder<S>(this).bind(view, viewModel)
}

/**
 * Bind [RxMviView] to [RxMviViewModel] view model
 */
class RxBinder<S : MviState>(private val lifecycleOwner: LifecycleOwner) {
    private val disposables = CompositeDisposable()

    init {
        lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                disposables.clear()
            }
        })
    }

    fun bind(view: RxMviView<S>, viewModel: RxMviViewModel<S>) {
        // Subscribe view model to view events
        disposables.add(
            view.events()
                .subscribe { event -> viewModel.process(event) }
        )
        // Subscribe view to view model state
        viewModel.state().observe(lifecycleOwner, Observer<S> { state ->
            view.render(state)
        })
    }
}
