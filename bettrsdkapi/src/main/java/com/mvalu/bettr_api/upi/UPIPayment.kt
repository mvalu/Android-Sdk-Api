package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object UPIPayment : ApiSdkBase() {
    private const val TAG = "UPI Payments"

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    private var verifyMerchantCallback: ApiResponseCallback<VerifyMerchantResult>? = null
    private var generateTokenCallback: ApiResponseCallback<UPIGenerateTokenResult>? = null

    fun verifyMerchant(
        verifyMerchantCallback: ApiResponseCallback<VerifyMerchantResult>,
        accountId: String,
        merchantVpa: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.verifyMerchantCallback = verifyMerchantCallback
        val request = VerifyMerchantRequest().apply {
            this.merchantVpa = merchantVpa
        }
        callApi(
            serviceApi.verifyMerchant(
                BettrApiSdk.getOrganizationId(),
                accountId,
                request
            ), ApiTag.VERIFY_MERCHANT_API
        )
    }

    fun upiGenerateToken(
        generateTokenCallback: ApiResponseCallback<UPIGenerateTokenResult>,
        accountId: String,
        deviceId: String,
        simSlotNo: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.generateTokenCallback = generateTokenCallback
        val request = UPIGenerateTokenRequest().apply {
            this.deviceId = deviceId
            this.simSlotNo = simSlotNo
        }
        callApi(
            serviceApi.upiGenerateToken(
                BettrApiSdk.getOrganizationId(),
                accountId,
                request
            ), ApiTag.UPI_GENERATE_TOKEN_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.VERIFY_MERCHANT_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Merchant verified")
                val verifyMerchantApiResponse = response as VerifyMerchantApiResponse
                verifyMerchantCallback?.onSuccess(verifyMerchantApiResponse.results!!)
            }

            ApiTag.UPI_GENERATE_TOKEN_API -> {
                BettrApiSdkLogger.printInfo(TAG, "upi token generated")
                val upiGenerateTokenApiResponse = response as UPIGenerateTokenApiResponse
                generateTokenCallback?.onSuccess(upiGenerateTokenApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.VERIFY_MERCHANT_API -> {
                verifyMerchantCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.UPI_GENERATE_TOKEN_API -> {
                generateTokenCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.VERIFY_MERCHANT_API -> {
                verifyMerchantCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.UPI_GENERATE_TOKEN_API -> {
                generateTokenCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.VERIFY_MERCHANT_API -> {
                verifyMerchantCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.UPI_GENERATE_TOKEN_API -> {
                generateTokenCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.VERIFY_MERCHANT_API -> {
                verifyMerchantCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.UPI_GENERATE_TOKEN_API -> {
                generateTokenCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}