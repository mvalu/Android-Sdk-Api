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

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Payment summary fetched")
                val paymentSummaryApiResponse = response as PaymentSummaryApiResponse
                paymentSummaryCallback?.onSuccess(paymentSummaryApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.PAYMENT_SUMMARY_API -> {
                paymentSummaryCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}