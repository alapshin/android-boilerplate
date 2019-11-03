package com.alapshin.mvi

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

typealias Reducer<S> = (state1: S, state2: S) -> S
typealias Processor<E, S> = (events: Observable<E>) -> Observable<S>

abstract class RxMviViewModel<E : MviEvent, S : MviState> : BaseMviViewModel<E, S>() {
    protected val disposables = CompositeDisposable()
    protected val eventRelay: BehaviorRelay<E> = BehaviorRelay.create()
    protected val stateRelay: BehaviorRelay<S> = BehaviorRelay.create()

    fun start() {
        addDisposable(
            eventRelay
                .publish(processor())
                .scan(reducer())
                .subscribe(stateRelay)
        )

        addDisposable(stateRelay
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { setState(it) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    override fun dispatch(event: E) {
        eventRelay.accept(event)
    }

    abstract fun reducer(): Reducer<S>
    abstract fun processor(): Processor<E, S>

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
