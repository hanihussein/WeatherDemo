package com.hani.weatherdemo.core.utils


data class DataState<T>(
        var message: String? = null,
        var loading: Boolean = false,
        var data: T? = null
) {
    companion object {
        fun <T> error(message: String) = DataState<T>(message)

        fun <T> loading(isLoading: Boolean, data: T? = null) = DataState<T>(
                null, isLoading, data
        )

        fun <T> data(message: String? = null, data: T) = DataState(
                message, false, data
        )
    }
}