package com.example.utilities

sealed class ResourceState<out T> {
    object Loading : ResourceState<Nothing>()
    data class Success<out T>(val data: T) : ResourceState<T>()
    data class Error(val error: Any) : ResourceState<Nothing>()
}
