package com.mvalu.bettr_api.payment

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.payment.summary.PaymentSummaryApiResponse
import com.mvalu.bettr_api.payment.summary.PaymentSummaryResult
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object Payment : ApiSdkBase() {
    private const val TAG = "Payments"

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    private var paymentSummaryCallback: ApiResponseCallback<PaymentSummaryResult>? = null
    private var generateOrderCallback: ApiResponseCallback<GenerateOrderResult>? = null
    private var paymentStatusCallback: ApiResponseCallback<PaymentStatusResult>? = null

    fun getPaymentSummary(
        paymentSummaryCallback: ApiResponseCallback<PaymentSummaryResult>,
        accountId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.paymentSummaryCallback = paymentSummaryCallback
        callApi(
            serviceApi.getPaymentSummary(
                BettrApiSdk.getOrganizationId(),
                accountId
            ), ApiTag.PAYMENT_SUMMARY_API
        )
    }

    fun generateOrderId(
        generateOrderCallback: ApiResponseCallback<GenerateOrderResult>,
        accountId: String,
        amount: Double
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.generateOrderCallback = generateOrderCallback
        val request = GenerateOrderApiRequest().apply {
            this.amount = amount
        }
        callApi(
            serviceApi.generateOrderId(
                BettrApiSdk.getOrganizationId(),
                accountId,
                request
            ), ApiTag.GENERATE_ORDER_API
        )
    }

    fun checkPaymentStatus(
        paymentStatusCallback: ApiResponseCallback<PaymentStatusResult>,
        paymentId: String?,
        orderId: String?
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.paymentStatusCallback = paymentStatusCallback
        val request = PaymentStatusRequest().apply {
            this.paymentId = paymentId
            this.orderId = orderId
        }
        callApi(
            serviceApi.checkPaymentStatus(
                BettrApiSdk.getOrganizationId(),
                request
            ), ApiTag.CHECK_PAYMENT_STATUS_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Payment summary fetched")
                val paymentSummaryApiResponse = response as PaymentSummaryApiResponse
                paymentSummaryCallback?.onSuccess(paymentSummaryApiResponse.results!!)
            }
            ApiTag.GENERATE_ORDER_API -> {
                BettrApiSdkLogger.printInfo(TAG, "order id generated")
                val generateOrderApiResponse = response as GenerateOrderApiResponse
                generateOrderCallback?.onSuccess(generateOrderApiResponse.results!!)
            }
            ApiTag.CHECK_PAYMENT_STATUS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "payment status fetched")
                val paymentStatusApiResponse = response as PaymentStatusApiResponse
                paymentStatusCallback?.onSuccess(paymentStatusApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(errorMessage)
            }
            ApiTag.PAYMENT_SUMMARY_API -> {
                generateOrderCallback?.onError(errorMessage)
            }
            ApiTag.CHECK_PAYMENT_STATUS_API -> {
                paymentStatusCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.PAYMENT_SUMMARY_API -> {
                generateOrderCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.CHECK_PAYMENT_STATUS_API -> {
                paymentStatusCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.PAYMENT_SUMMARY_API -> {
                generateOrderCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.CHECK_PAYMENT_STATUS_API -> {
                paymentStatusCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.PAYMENT_SUMMARY_API -> {
                generateOrderCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.CHECK_PAYMENT_STATUS_API -> {
                paymentStatusCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}