package com.mvalu.bettr_api

interface BettrApiSdkCallback {
    fun onSuccess()
    fun onError(error: String)
}