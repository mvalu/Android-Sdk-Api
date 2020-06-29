package com.mvalu.bettr_api.account_statements

import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class AccountStatementsResult {
    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    @field:Json(name = "statementList")
    var statementList: List<StatementInfo>? = null
}