package com.mvalu.bettr_api.downloads

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object DocumentDownload : ApiSdkBase() {
    private const val TAG = "DownloadDoc"
    private var downloadDocCallback: ApiResponseCallback<String>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getStatementDownloadUrl(
        downloadDocCallback: ApiResponseCallback<String>,
        accountId: String,
        statementId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.downloadDocCallback = downloadDocCallback
        callApi(
            serviceApi.getStatementDownloadLink(
                BettrApiSdk.getOrganizationId(),
                accountId,
                statementId
            ), ApiTag.DOWNLOAD_LINK_STATEMENT_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.DOWNLOAD_LINK_STATEMENT_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Statement download link fetched")
                val downloadDocApiResponse = response as DocumentDownloadApiResponse
                downloadDocCallback?.onSuccess(downloadDocApiResponse.results?.fileUrl ?: "")
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.DOWNLOAD_LINK_STATEMENT_API -> {
                downloadDocCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
        )
        when (apiTag) {
            ApiTag.DOWNLOAD_LINK_STATEMENT_API -> {
                downloadDocCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
        )
        when (apiTag) {
            ApiTag.DOWNLOAD_LINK_STATEMENT_API -> {
                downloadDocCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.DOWNLOAD_LINK_STATEMENT_API -> {
                downloadDocCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}