package com.mvalu.bettr_api.search

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object SearchFieldData : ApiSdkBase() {
    private const val TAG = "SearchFieldData"
    private var searchFieldDataCallback: ApiResponseCallback<SearchFieldDataApiResponse>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getSearchData(
        searchFieldDataCallback: ApiResponseCallback<SearchFieldDataApiResponse>,
        configKey: String,
        searchString: Map<String, String>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.searchFieldDataCallback = searchFieldDataCallback
        callApi(
            serviceApi.getSearchFieldDataResponse(
                BettrApiSdk.getOrganizationId(),
                configKey,
                searchString
            ), ApiTag.SEARCH_FIELD_DATA
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.SEARCH_FIELD_DATA -> {
                BettrApiSdkLogger.printInfo(TAG, "search field data fetched")
                val searchFieldDataApiResponse = response as SearchFieldDataApiResponse
                searchFieldDataCallback?.onSuccess(searchFieldDataApiResponse)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                searchFieldDataCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                searchFieldDataCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                searchFieldDataCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                searchFieldDataCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}