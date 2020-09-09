package com.mvalu.bettr_api.home_module

import com.mvalu.bettr_api.account_statements.StatementInfo
import com.mvalu.bettr_api.account_statements.transactions.StatementTransactionInfo
import com.squareup.moshi.Json

class HomeModuleResults {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "recentTransaction")
    var recentTransactions: List<StatementTransactionInfo>? = null

    @field:Json(name = "statementSummary")
    var statementSummary: StatementInfo? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    @field:Json(name = "dueInfo")
    var dueInfo: DueInfo? = null

    @field:Json(name = "activatePlasticCardOption")
    var showActivatePlasticCard: Boolean? = false

    @field:Json(name = "activateDigitalCardOption")
    var showOrderNewCard: Boolean? = false

    @field:Json(name = "requiredPinSet")
    var requiredPinSet: Boolean? = false
}