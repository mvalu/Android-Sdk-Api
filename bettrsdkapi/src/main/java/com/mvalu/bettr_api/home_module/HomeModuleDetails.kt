package com.mvalu.bettr_api.home_module

import com.squareup.moshi.Json

class HomeModuleDetails {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "recentTransaction")
    var recentTransactions: List<RecentTransaction>? = null

    @field:Json(name = "statementSummary")
    var statementSummary: StatementSummary? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null
}