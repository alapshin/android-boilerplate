package com.happify.mvi.rx

import com.happify.mvi.core.MviEvent
import com.happify.mvi.core.MviViewModel
import com.happify.mvi.core.MviState
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Function that produces new state based on existing one and incoming one
 */
typealias Reducer<S> = (oldState: S, newState: S) -> S

/**
 * Function that transforms stream of events into the stream of states
 */
typealias Processor<S> = (events: Observable<MviEvent>) -> Observable<S>

/**
 * [MviViewModel] implementation using RxJava under the hood
 */
abstract class RxMviViewModel<S : MviState> : MviViewModel<S>() {
    protected val disposables = CompositeDisposable()
    protected val stateRelay: BehaviorRelay<S> = BehaviorRelay.create()
    protected val eventRelay: BehaviorRelay<MviEvent> = BehaviorRelay.createDefault(MviEvent.Default)

    override fun start() {
        addDisposable(
            eventRelay
                .publish(processor())
                .scan(reducer())
                .subscribe(stateRelay)
        )

        addDisposable(stateRelay
            .subscribe { state.postValue(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    override fun process(event: MviEvent) {
        eventRelay.accept(event)
    }

    abstract fun reducer(): Reducer<S>
    abstract fun processor(): Processor<S>

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
