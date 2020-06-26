package com.mvalu.bettr_api.home_module

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object HomeModule : ApiSdkBase() {
    private const val TAG = "HomeModule"
    private var homeModuleDetailsCallback: ApiResponseCallback<List<HomeModuleDetails>>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getHomeModuleDetails(homeModuleDetailsCallback: ApiResponseCallback<List<HomeModuleDetails>>) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.homeModuleDetailsCallback = homeModuleDetailsCallback
        callApi(
            serviceApi.getCardHomeModule(BettrApiSdk.getOrganizationId()),
            ApiTag.HOME_MODULE_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Home Module details fetched")
                val homeModuleApiResponse = response as HomeModuleApiResponse
                homeModuleDetailsCallback?.onSuccess(homeModuleApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
                homeModuleDetailsCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
                )
                homeModuleDetailsCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
                )
                homeModuleDetailsCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.AUTH_ERROR.value
                )
                homeModuleDetailsCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}