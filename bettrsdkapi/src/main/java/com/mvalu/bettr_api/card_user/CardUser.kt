package com.mvalu.bettr_api.card_user

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object CardUser : ApiSdkBase() {
    private const val TAG = "CardUser"
    private var cardUserCallback: ApiResponseCallback<CardUserResponse>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getUserDetails(cardUserRequest: CardUserRequest, cardUserCallback: ApiResponseCallback<CardUserResponse>) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.cardUserCallback = cardUserCallback
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.GET_USER_API -> {
                BettrApiSdkLogger.printInfo(TAG, "User details fetched")
                val userDetailsResponse = response as CardUserResponse
                cardUserCallback?.onSuccess(userDetailsResponse)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        when (apiTag) {
            ApiTag.GET_USER_API, ApiTag.UPDATE_USER_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
                cardUserCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GET_USER_API, ApiTag.UPDATE_USER_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
                )
                cardUserCallback?.onError(ErrorMessage.SDK_INITIALIZATION_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GET_USER_API, ApiTag.UPDATE_USER_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
                )
                cardUserCallback?.onError(ErrorMessage.SDK_INITIALIZATION_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GET_USER_API, ApiTag.UPDATE_USER_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.AUTH_ERROR.value
                )
                cardUserCallback?.onError(ErrorMessage.SDK_INITIALIZATION_ERROR.value)
            }
        }
    }
}