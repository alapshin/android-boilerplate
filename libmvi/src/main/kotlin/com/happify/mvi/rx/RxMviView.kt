package com.happify.mvi.rx

import com.happify.mvi.core.MviEvent
import com.happify.mvi.core.MviView
import com.happify.mvi.core.MviState
import io.reactivex.Observable

/**
 * [MviView] implementation that provides events as [Observable]
 */
interface RxMviView<S : MviState> : MviView<S> {
    fun events(): Observable<MviEvent>
}
