package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object Settings : ApiSdkBase() {

    private const val TAG = "Settings"
    private var settingsResponseCallback: ApiResponseCallback<SettingsInfo>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getSettingsInfo(
        settingsResponseCallback: ApiResponseCallback<SettingsInfo>,
        accountId: String,
        cardId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.settingsResponseCallback = settingsResponseCallback
        callApi(
            serviceApi.getSettingsInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId
            ),
            ApiTag.SETTINGS_INFO_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Settings info fetched")
                val settingsInfoApiResponse = response as SettingsInfoApiResponse
                settingsResponseCallback?.onSuccess(settingsInfoApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
        )
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
        )
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}