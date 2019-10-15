package com.alapshin.boilerplate.common.paging

sealed class NetworkState {
    class Loading : NetworkState()
    class Success : NetworkState()
    data class Error(val throwable: Throwable) : NetworkState()
}
