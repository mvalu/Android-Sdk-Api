package com.mvalu.bettr_api.account_statements

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionInfoApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsResult
import com.mvalu.bettr_api.account_statements.transactions.StatementTransactionInfo
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object AccountStatements : ApiSdkBase() {
    private const val TAG = "AccountStatements"
    private var accountStatementsCallback: ApiResponseCallback<AccountStatementsResult>? = null
    private var accountStatementTransactionsCallback: ApiResponseCallback<AccountStatementTransactionsResult>? =
        null
    private var accountStatementTransactionInfoCallback: ApiResponseCallback<StatementTransactionInfo>? =
        null
    private var statementDetailSummaryCallback: ApiResponseCallback<List<StatementSummaryItem>>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getAccountStatements(
        accountStatementsCallback: ApiResponseCallback<AccountStatementsResult>,
        accountId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.accountStatementsCallback = accountStatementsCallback
        callApi(
            serviceApi.getAccountStatements(
                BettrApiSdk.getOrganizationId(),
                accountId
            ),
            ApiTag.ACCOUNT_STATEMENTS_API
        )
    }

    fun getAccountStatementTransactions(
        accountStatementTransactionsCallback: ApiResponseCallback<AccountStatementTransactionsResult>,
        accountId: String,
        statementId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.accountStatementTransactionsCallback = accountStatementTransactionsCallback
        callApi(
            serviceApi.getAccountStatementTransactions(
                BettrApiSdk.getOrganizationId(),
                accountId,
                statementId
            ),
            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API
        )
    }

    fun getAccountStatementTransactionInfo(
        accountStatementTransactionInfoCallback: ApiResponseCallback<StatementTransactionInfo>,
        accountId: String,
        statementTransactionId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.accountStatementTransactionInfoCallback = accountStatementTransactionInfoCallback
        callApi(
            serviceApi.getAccountStatementTransactionInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                statementTransactionId
            ),
            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_INFO_API
        )
    }

    fun getAccountStatementDetailSummary(
        statementDetailSummaryCallback: ApiResponseCallback<List<StatementSummaryItem>>,
        accountId: String,
        statementId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.statementDetailSummaryCallback = statementDetailSummaryCallback
        callApi(
            serviceApi.getAccountStatementDetailSummary(
                BettrApiSdk.getOrganizationId(),
                accountId,
                statementId
            ),
            ApiTag.ACCOUNT_STATEMENT_DETAIL_SUMMARY_API
        )
    }


    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account Statements fetched")
                val accountStatementsApiResponse = response as AccountStatementsApiResponse
                accountStatementsCallback?.onSuccess(accountStatementsApiResponse.results!!)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account Statement Transactions fetched")
                val accountStatementTransactionsApiResponse =
                    response as AccountStatementTransactionsApiResponse
                accountStatementTransactionsCallback?.onSuccess(
                    accountStatementTransactionsApiResponse.results!!
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account Statement Transaction Info fetched")
                val accountStatementTransactionInfoApiResponse =
                    response as AccountStatementTransactionInfoApiResponse
                accountStatementTransactionInfoCallback?.onSuccess(
                    accountStatementTransactionInfoApiResponse.results!!
                )
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                accountStatementsCallback?.onError(errorCode, errorMessage)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(errorCode, errorMessage)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_INFO_API -> {
                accountStatementTransactionInfoCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
        )
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                accountStatementsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_INFO_API -> {
                accountStatementTransactionInfoCallback?.onError(
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
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                accountStatementsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_INFO_API -> {
                accountStatementTransactionInfoCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.AUTH_ERROR.value
        )
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                accountStatementsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_INFO_API -> {
                accountStatementTransactionInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}