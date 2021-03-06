package com.romazelenin.news.domain.entity

sealed class AppState {
    object Starting:AppState()
    object Loading : AppState()
    class Error<T>(val throwable: Throwable? = null, val data: T? = null) : AppState()
    class Success<T>(val data: T) : AppState()
}
