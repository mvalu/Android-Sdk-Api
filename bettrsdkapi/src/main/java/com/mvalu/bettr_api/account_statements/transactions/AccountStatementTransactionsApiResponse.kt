package com.mvalu.bettr_api.account_statements.transactions

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class AccountStatementTransactionsApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: AccountStatementTransactionsResult? = null
}