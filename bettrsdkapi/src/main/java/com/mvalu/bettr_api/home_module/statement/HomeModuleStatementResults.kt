package com.mvalu.bettr_api.home_module.statement

import com.mvalu.bettr_api.account_statements.StatementInfo
import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class HomeModuleStatementResults {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "latestStatements")
    var latestStatements: List<StatementInfo>? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    @field:Json(name = "paymentTotalAmountSummary")
    var paymentTotalAmountSummary: SummaryCardDetails? = null

    @field:Json(name = "statementTotalAmountSummary")
    var statementTotalAmountSummary: SummaryCardDetails? = null

    @field:Json(name = "spendTotalAmountSummary")
    var spendTotalAmountSummary: SummaryCardDetails? = null

    @field:Json(name = "spendAnalysisTotalAmountSummary")
    var spendAnalysisTotalAmountSummary: SummaryCardDetails? = null

    class SummaryCardDetails {
        @field:Json(name = "amount")
        var amount: Double? = null
    }
}