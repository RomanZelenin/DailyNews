package com.romazelenin.news.domain

sealed class AppState {
    object Loading : AppState()
    class Error<T>(val throwable: Throwable? = null, val data: T? = null) : AppState()
    class Success<T>(val data: T) : AppState()
}
