package com.mvalu.bettr_api.transactions

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.transactions.payments.CardPaymentsApiResponse
import com.mvalu.bettr_api.transactions.payments.CardPaymentsResult
import com.mvalu.bettr_api.transactions.payments.PaymentInfo
import com.mvalu.bettr_api.transactions.payments.PaymentInfoApiResponse
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object CardTransactions : ApiSdkBase() {
    private const val TAG = "CardTransactions"
    private var statementTransactionsCallback: ApiResponseCallback<CardTransactionsResults>? = null
    private var paymentsListCallback: ApiResponseCallback<CardPaymentsResult>? = null
    private var accountTransactionsListCallback: ApiResponseCallback<AccountTransactionsResults>? =
        null
    private var transactionAnalysisCallback: ApiResponseCallback<TransactionAnalysisResult>? =
        null
    private var paymentInfoCallback: ApiResponseCallback<PaymentInfo>? = null
    private var transactionInfoCallback: ApiResponseCallback<TransactionInfo>? = null

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

    fun getTransactionsAnalysis(
        transactionAnalysisCallback: ApiResponseCallback<TransactionAnalysisResult>,
        accountId: String,
        startDate: String,
        endDate: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.transactionAnalysisCallback = transactionAnalysisCallback
        callApi(
            serviceApi.getTransactionsAnalysis(
                BettrApiSdk.getOrganizationId(),
                accountId,
                startDate,
                endDate
            ), ApiTag.TRANSACTIONS_ANALYSIS_API
        )
    }

    fun getPaymentInfo(
        paymentInfoCallback: ApiResponseCallback<PaymentInfo>,
        accountId: String,
        paymentId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.paymentInfoCallback = paymentInfoCallback
        callApi(
            serviceApi.getPaymentInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                paymentId
            ),
            ApiTag.PAYMENT_INFO_API
        )
    }

    fun getTransactionInfo(
        transactionInfoCallback: ApiResponseCallback<TransactionInfo>,
        accountId: String,
        transactionId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.transactionInfoCallback = transactionInfoCallback
        callApi(
            serviceApi.getTransactionInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                transactionId
            ),
            ApiTag.TRANSACTION_INFO_API
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
            ApiTag.TRANSACTIONS_ANALYSIS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Transactions analysis fetched")
                val transactionsAnalysisApiResponse = response as TransactionAnalysisApiResponse
                transactionAnalysisCallback?.onSuccess(transactionsAnalysisApiResponse.results!!)
            }
            ApiTag.PAYMENT_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Payment info fetched")
                val paymentInfoApiResponse = response as PaymentInfoApiResponse
                paymentInfoCallback?.onSuccess(paymentInfoApiResponse.results!!)
            }
            ApiTag.TRANSACTION_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Transaction info fetched")
                val transactionInfoApiResponse = response as TransactionInfoApiResponse
                transactionInfoCallback?.onSuccess(transactionInfoApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.TRANSACTIONS_ANALYSIS_API -> {
                transactionAnalysisCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.PAYMENT_INFO_API -> {
                paymentInfoCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.TRANSACTION_INFO_API -> {
                transactionInfoCallback?.onError(errorCode, errorMessage)
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
                statementTransactionsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.TRANSACTIONS_ANALYSIS_API -> {
                transactionAnalysisCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.PAYMENT_INFO_API -> {
                paymentInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.TRANSACTION_INFO_API -> {
                transactionInfoCallback?.onError(
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
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.TRANSACTIONS_ANALYSIS_API -> {
                transactionAnalysisCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.PAYMENT_INFO_API -> {
                paymentInfoCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.TRANSACTION_INFO_API -> {
                transactionInfoCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.STATEMENT_TRANSACTIONS_API -> {
                statementTransactionsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.CARD_PAYMENTS_API -> {
                paymentsListCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.ACCOUNT_TRANSACTIONS_API -> {
                accountTransactionsListCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.TRANSACTIONS_ANALYSIS_API -> {
                transactionAnalysisCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.PAYMENT_INFO_API -> {
                paymentInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.TRANSACTION_INFO_API -> {
                transactionInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}