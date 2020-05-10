package com.mvalu.bettr_api.internal

enum class ErrorMessage(val value: String) {
    SDK_NOT_INITIALIZED_ERROR("Sdk not initialized"),
    SDK_INITIALIZATION_ERROR("Sdk initialization failed"),
    API_TIMEOUT_ERROR("Api time out error"),
    NETWORK_ERROR("Network connection error"),
    AUTH_ERROR("Authentication error"),
}