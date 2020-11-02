package com.mvalu.bettr_api.network

interface ApiResponseCallback<T> {
    fun onSuccess(response: T)
    fun onError(errorCode: Int, errorMessage: String)
}