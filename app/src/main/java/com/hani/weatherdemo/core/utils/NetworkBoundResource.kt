package com.hani.weatherdemo.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.*

abstract class NetworkBoundResource<ResponseObject, ViewStateType> {

    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(Dispatchers.IO) {

            val apiResponse = createCall()

            withContext(Dispatchers.Main) {
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    handleNetworkCall(response)
                }
            }
        }
    }

    private fun handleNetworkCall(apiResponse: GenericApiResponse<ResponseObject>) {

        when (apiResponse) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(apiResponse)
            }

            is ApiErrorResponse -> {

                onReturnError(apiResponse.errorMessage)
            }

            is ApiEmptyResponse -> {

                onReturnError("HTTP 204 is Empty")
            }
        }
    }

    private fun onReturnError(message: String) {
        result.value = DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>

}