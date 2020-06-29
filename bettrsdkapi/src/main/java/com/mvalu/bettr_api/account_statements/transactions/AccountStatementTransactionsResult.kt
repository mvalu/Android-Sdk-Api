package com.mvalu.bettr_api.account_statements.transactions

import com.mvalu.bettr_api.account_statements.StatementInfo
import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class AccountStatementTransactionsResult {
    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    @field:Json(name = "statementInfo")
    var statementInfo: StatementInfo? = null

    @field:Json(name = "statementTransactionList")
    var statementTransactionList: List<StatementTransactionInfo>? = null
}