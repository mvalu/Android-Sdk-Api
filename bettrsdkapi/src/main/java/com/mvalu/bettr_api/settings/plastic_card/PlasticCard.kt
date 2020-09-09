package com.mvalu.bettr_api.settings.plastic_card

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.settings.SettingsGenericApiResponse
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object PlasticCard : ApiSdkBase() {

    private const val TAG = "PlasticCard"
    private var genericResponseCallback: ApiResponseCallback<Boolean>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun verifyCardNumber(
        genericResponseCallback: ApiResponseCallback<Boolean>,
        accountId: String,
        cardId: String,
        last4digits: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.genericResponseCallback = genericResponseCallback
        callApi(
            serviceApi.verifyCardNumber(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId,
                CardNumberVerifyRequest(last4digits)
            ),
            ApiTag.VERIFY_CARD_NUMBER_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.VERIFY_CARD_NUMBER_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Verified card number")
                val genericApiResponse = response as SettingsGenericApiResponse
                genericResponseCallback?.onSuccess(genericApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.VERIFY_CARD_NUMBER_API -> {
                genericResponseCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.VERIFY_CARD_NUMBER_API -> {
                genericResponseCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.VERIFY_CARD_NUMBER_API -> {
                genericResponseCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.VERIFY_CARD_NUMBER_API -> {
                genericResponseCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}