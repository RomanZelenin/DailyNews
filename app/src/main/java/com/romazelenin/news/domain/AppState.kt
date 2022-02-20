package com.romazelenin.news.domain

sealed class AppState<T> {
    object Loading : AppState<Nothing>()
    class Error<T>(throwable: Throwable, data: T?) : AppState<T>()
    class Success<T>(data: T) : AppState<T>()
}
