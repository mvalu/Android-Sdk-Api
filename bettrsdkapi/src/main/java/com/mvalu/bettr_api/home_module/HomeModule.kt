package com.mvalu.bettr_api.home_module

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.home_module.statement.HomeModuleStatementApiResponse
import com.mvalu.bettr_api.home_module.statement.HomeModuleStatementResults
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object HomeModule : ApiSdkBase() {
    private const val TAG = "HomeModule"
    private var homeModuleDetailsCallback: ApiResponseCallback<List<HomeModuleResults>>? = null
    private var homeModuleStatementDetailsCallback: ApiResponseCallback<List<HomeModuleStatementResults>>? =
        null

    private var accountInfoCallBack: ApiResponseCallback<AccountInfo>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getHomeModuleDetails(homeModuleDetailsCallback: ApiResponseCallback<List<HomeModuleResults>>) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.homeModuleDetailsCallback = homeModuleDetailsCallback
        callApi(
            serviceApi.getCardHomeModule(BettrApiSdk.getOrganizationId()),
            ApiTag.HOME_MODULE_API
        )
    }

    fun getHomeModuleStatementDetails(homeModuleStatementDetailsCallback: ApiResponseCallback<List<HomeModuleStatementResults>>) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.homeModuleStatementDetailsCallback = homeModuleStatementDetailsCallback
        callApi(
            serviceApi.getCardHomeModuleStatement(BettrApiSdk.getOrganizationId()),
            ApiTag.HOME_MODULE_STATEMENT_API
        )
    }

    fun getAccountInfo(accountInfoCallBack: ApiResponseCallback<AccountInfo>, accountId: String) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.accountInfoCallBack = accountInfoCallBack
        callApi(
            serviceApi.getAccountInfo(BettrApiSdk.getOrganizationId(), accountId),
            ApiTag.ACCOUNT_INFO_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Home Module details fetched")
                val homeModuleApiResponse = response as HomeModuleApiResponse
                homeModuleDetailsCallback?.onSuccess(homeModuleApiResponse.results!!)
            }
            ApiTag.HOME_MODULE_STATEMENT_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Home Module statement details fetched")
                val homeModuleStatementApiResponse = response as HomeModuleStatementApiResponse
                homeModuleStatementDetailsCallback?.onSuccess(homeModuleStatementApiResponse.results!!)
            }
            ApiTag.ACCOUNT_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account info fetched")
                val accountInfoApiResponse = response as AccountInfoApiResponse
                accountInfoCallBack?.onSuccess(accountInfoApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        when (apiTag) {
            ApiTag.HOME_MODULE_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
                homeModuleDetailsCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.HOME_MODULE_STATEMENT_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
                homeModuleStatementDetailsCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.ACCOUNT_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
                accountInfoCallBack?.onError(errorCode, errorMessage)
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
                homeModuleDetailsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.HOME_MODULE_STATEMENT_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
                )
                homeModuleStatementDetailsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.ACCOUNT_INFO_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
                )
                accountInfoCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
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
                homeModuleDetailsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.HOME_MODULE_STATEMENT_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
                )
                homeModuleStatementDetailsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.ACCOUNT_INFO_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
                )
                accountInfoCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
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
                homeModuleDetailsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.HOME_MODULE_STATEMENT_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.AUTH_ERROR.value
                )
                homeModuleStatementDetailsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.ACCOUNT_INFO_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.AUTH_ERROR.value
                )
                accountInfoCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}