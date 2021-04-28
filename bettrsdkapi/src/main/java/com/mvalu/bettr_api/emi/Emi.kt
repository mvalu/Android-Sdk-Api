package com.mvalu.bettr_api.emi

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object Emi : ApiSdkBase() {
    private const val TAG = "Emi"
    private var ConvertToEmiCallback: ApiResponseCallback<EmiInfo>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun convertToEmi(
        ConvertToEmiCallback: ApiResponseCallback<EmiInfo>,
        accountId: String,
        transactionId: String,
        duration: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.ConvertToEmiCallback = ConvertToEmiCallback
        callApi(
            serviceApi.convertToEmi(
                BettrApiSdk.getOrganizationId(),
                accountId,
                transactionId,
                ConvertToEmiApiRequest(duration)
            ),
            ApiTag.CONVERT_TO_EMI_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Convert to api response fetched")
                val convertToEmiApiResponse = response as ConvertToEmiApiResponse
                ConvertToEmiCallback?.onSuccess(convertToEmiApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                ConvertToEmiCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                ConvertToEmiCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                ConvertToEmiCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                ConvertToEmiCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}