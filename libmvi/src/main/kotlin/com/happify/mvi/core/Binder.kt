package com.happify.mvi.core

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

fun <S : MviState> Fragment.bind(view: MviView<S>, viewModel: MviViewModel<S>) {
    Binder<S>(viewLifecycleOwner).bind(view, viewModel)
}

fun <S : MviState> ComponentActivity.bind(view: MviView<S>, viewModel: MviViewModel<S>) {
    Binder<S>(this).bind(view, viewModel)
}

/**
 * Bind [MviView] to [MviViewModel] model.
 *
 * This binder implementation assumes that view events are passed to view model manually using[MviViewModel.process].
 */
class Binder<S : MviState>(private val lifecycleOwner: LifecycleOwner) {
    fun bind(view: MviView<S>, viewModel: MviViewModel<S>) {
        // Subscribe view to view model state
        viewModel.state().observe(lifecycleOwner, Observer<S> { state ->
            view.render(state)
        })
    }
}
