package com.mvalu.bettr_api.transactions

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.transactions.payments.CardPaymentsApiResponse
import com.mvalu.bettr_api.transactions.payments.CardPaymentsResult
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object CardTransactions : ApiSdkBase() {
    private const val TAG = "CardTransactions"
    private var statementTransactionsCallback: ApiResponseCallback<CardTransactionsResults>? = null
    private var paymentsListCallback: ApiResponseCallback<CardPaymentsResult>? = null
    private var accountTransactionsListCallback: ApiResponseCallback<AccountTransactionsResults>? =
        null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getStatementTransactions(
        statementTransactionsCallback: ApiResponseCallback<CardTransactionsResults>,
        accountId: String,
        type: String?,
        startMonth: String?,
        endMonth: String?,
        status: String?,
        amountStart: Int?,
        amountEnd: Int?,
        category: String?
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.statementTransactionsCallback = statementTransactionsCallback
        callApi(
            serviceApi.getStatementTransactions(
                BettrApiSdk.getOrganizationId(),
                accountId,
                type,
                startMonth,
                endMonth,
                status,
                amountStart,
                amountEnd,
                category
            ),
            ApiTag.STATEMENT_TRANSACTIONS_API
        )
    }

    fun getPaymentsList(
        paymentsListCallback: ApiResponseCallback<CardPaymentsResult>,
        accountId: String,
        startMonth: String,
        endMonth: String,
        status: String,
        source: String,
        offset: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.paymentsListCallback = paymentsListCallback
        callApi(
            serviceApi.getCardPayments(
                BettrApiSdk.getOrganizationId(),
                accountId,
                source,
                startMonth,
                endMonth,
                status,
                offset
            ),
            ApiTag.CARD_PAYMENTS_API
        )
    }

    fun getAccountTransactions(
        accountTransactionsListCallback: ApiResponseCallback<AccountTransactionsResults>,
        accountId: String,
        startMonth: String?,
        endMonth: String?,
        amountStart: String?,
        amountEnd: String?,
        merchantCategory: String?,
        startDate: String?,
        endDate: String?,
        status: String?,
        search: String?,
        offset: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.accountTransactionsListCallback = accountTransactionsListCallback
        callApi(
            serviceApi.getAccountTransactions(
                BettrApiSdk.getOrganizationId(),
                accountId,
                startMonth,
                endMonth,
                amountStart,
                amountEnd,
                merchantCategory,
                startDate,
                endDate,
                status,
                search,
                offset
            ), ApiTag.ACCOUNT_TRANSACTIONS_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Statement Transactions fetched")
                val statementTransactionsApiResponse = response as CardTransactionsApiResponse
                statementTransactionsCallback?.onSuccess(statementTransactionsApiResponse.results!!)
            }
            ApiTag.CARD_PAYMENTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Card payments fetched")
                val cardPaymentsApiResponse = response as CardPaymentsApiResponse
                paymentsListCallback?.onSuccess(cardPaymentsApiResponse.results!!)
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account transactions fetched")
                val accountTransactionsApiResponse = response as AccountTransactionsApiResponse
                accountTransactionsListCallback?.onSuccess(accountTransactionsApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(errorMessage)
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(errorMessage)
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
        )
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
        )
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}