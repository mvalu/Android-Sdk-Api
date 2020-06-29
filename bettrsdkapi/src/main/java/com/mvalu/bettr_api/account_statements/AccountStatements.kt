package com.mvalu.bettr_api.account_statements

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsResult
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object AccountStatements : ApiSdkBase() {
    private const val TAG = "AccountStatements"
    private var accountStatementsCallback: ApiResponseCallback<AccountStatementsResult>? = null
    private var accountStatementTransactionsCallback: ApiResponseCallback<AccountStatementTransactionsResult>? = null

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


    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account Statements fetched")
                val accountStatementsApiResponse = response as AccountStatementsApiResponse
                accountStatementsCallback?.onSuccess(accountStatementsApiResponse.results!!)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Account Statement Transactions fetched")
                val accountStatementTransactionsApiResponse = response as AccountStatementTransactionsApiResponse
                accountStatementTransactionsCallback?.onSuccess(accountStatementTransactionsApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.ACCOUNT_STATEMENTS_API -> {
                accountStatementsCallback?.onError(errorMessage)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(errorMessage)
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
                accountStatementsCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
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
                accountStatementsCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
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
                accountStatementsCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }

            ApiTag.ACCOUNT_STATEMENT_TRANSACTIONS_API -> {
                accountStatementTransactionsCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}