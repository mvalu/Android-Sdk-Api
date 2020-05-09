package com.mvalu.bettr_api.login

interface GenerateTokenCallback {
    fun onSuccess(generateTokenResponse: GenerateTokenResponse)
    fun onError(errorMessage: String)
}