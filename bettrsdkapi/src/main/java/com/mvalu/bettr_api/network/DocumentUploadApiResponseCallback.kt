package com.mvalu.bettr_api.network

interface DocumentUploadApiResponseCallback<T> {
    fun onSuccess(response: T)
    fun onError(errorMessage: String)
    fun progressUpdate(progressPercentage: Int)
}