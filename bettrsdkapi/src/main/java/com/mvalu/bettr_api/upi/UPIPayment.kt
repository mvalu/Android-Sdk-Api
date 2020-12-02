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
    private var upiSetUpCallback: ApiResponseCallback<UPISetUpResult>? = null
    private var upiPaymentCallback: ApiResponseCallback<UPIPaymentResult>? = null
    private var recentMerchantsCallback: ApiResponseCallback<List<MerchantDetail>>? = null

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

    fun upiSetUp(
        upiSetUpCallback: ApiResponseCallback<UPISetUpResult>,
        accountId: String,
        tokenId: String,
        appVersion: String,
        deviceApp: String,
        deviceModel: String,
        deviceOs: String,
        deviceType: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.upiSetUpCallback = upiSetUpCallback
        val request = UPISetUpRequest().apply {
            this.tokenId = tokenId
            this.appVersion = appVersion
            this.deviceApp = deviceApp
            this.deviceModel = deviceModel
            this.deviceOs = deviceOs
            this.deviceType = deviceType
        }
        callApi(
            serviceApi.upiSetUp(
                BettrApiSdk.getOrganizationId(),
                accountId,
                request
            ), ApiTag.UPI_SETUP_API
        )
    }

    fun upiPayment(
        upiPaymentCallback: ApiResponseCallback<UPIPaymentResult>,
        accountId: String,
        upiMerchantId: String,
        amount: Double
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.upiPaymentCallback = upiPaymentCallback
        val request = UPIPaymentRequest().apply {
            this.upiMerchantId = upiMerchantId
            this.amount = amount
        }
        callApi(
            serviceApi.makeUPIPayment(
                BettrApiSdk.getOrganizationId(),
                accountId,
                request
            ), ApiTag.UPI_PAYMENT_API
        )
    }

    fun getRecentMerchants(
        recentMerchantsCallback: ApiResponseCallback<List<MerchantDetail>>,
        accountId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.recentMerchantsCallback = recentMerchantsCallback
        callApi(
            serviceApi.getRecentMerchants(
                BettrApiSdk.getOrganizationId(),
                accountId
            ), ApiTag.RECENT_MERCHANTS_API
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

            ApiTag.UPI_SETUP_API -> {
                BettrApiSdkLogger.printInfo(TAG, "upi setup success")
                val upiSetUpApiResponse = response as UPISetUpApiResponse
                upiSetUpCallback?.onSuccess(upiSetUpApiResponse.results!!)
            }

            ApiTag.UPI_PAYMENT_API -> {
                BettrApiSdkLogger.printInfo(TAG, "upi payment success")
                val upiPaymentApiResponse = response as UPIPaymentApiResponse
                upiPaymentCallback?.onSuccess(upiPaymentApiResponse.results!!)
            }

            ApiTag.RECENT_MERCHANTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "recent merchants success")
                val recentMerchantsApiResponse = response as RecentMerchantsApiResponse
                recentMerchantsCallback?.onSuccess(recentMerchantsApiResponse.results!!)
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
            ApiTag.UPI_SETUP_API -> {
                upiSetUpCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.UPI_PAYMENT_API -> {
                upiPaymentCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.RECENT_MERCHANTS_API -> {
                recentMerchantsCallback?.onError(errorCode, errorMessage)
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
            ApiTag.UPI_SETUP_API -> {
                upiSetUpCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.UPI_PAYMENT_API -> {
                upiPaymentCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.RECENT_MERCHANTS_API -> {
                recentMerchantsCallback?.onError(
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
            ApiTag.UPI_SETUP_API -> {
                upiSetUpCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.UPI_PAYMENT_API -> {
                upiPaymentCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.RECENT_MERCHANTS_API -> {
                recentMerchantsCallback?.onError(
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
            ApiTag.UPI_SETUP_API -> {
                upiSetUpCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.UPI_PAYMENT_API -> {
                upiPaymentCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.RECENT_MERCHANTS_API -> {
                recentMerchantsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}