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
    private var convertToEmiCallback: ApiResponseCallback<EmiInfo>? = null
    private var emiInfoCallback: ApiResponseCallback<EmiInfo>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun convertToEmi(
        convertToEmiCallback: ApiResponseCallback<EmiInfo>,
        accountId: String,
        transactionId: String,
        duration: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.convertToEmiCallback = convertToEmiCallback
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

    fun getTransactionEmiInfo(
        emiInfoCallback: ApiResponseCallback<EmiInfo>,
        accountId: String,
        transactionId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.emiInfoCallback = emiInfoCallback
        callApi(
            serviceApi.getTransactionEmiInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                transactionId
            ),
            ApiTag.TRANSACTION_EMI_INFO_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Convert to api response fetched")
                val convertToEmiApiResponse = response as ConvertToEmiApiResponse
                convertToEmiCallback?.onSuccess(convertToEmiApiResponse.results!!)
            }
            ApiTag.TRANSACTION_EMI_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Transaction emi info fetched")
                val convertToEmiApiResponse = response as ConvertToEmiApiResponse
                emiInfoCallback?.onSuccess(convertToEmiApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                convertToEmiCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.TRANSACTION_EMI_INFO_API -> {
                emiInfoCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.CONVERT_TO_EMI_API -> {
                convertToEmiCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.TRANSACTION_EMI_INFO_API -> {
                emiInfoCallback?.onError(
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
                convertToEmiCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.TRANSACTION_EMI_INFO_API -> {
                emiInfoCallback?.onError(
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
                convertToEmiCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.TRANSACTION_EMI_INFO_API -> {
                emiInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}