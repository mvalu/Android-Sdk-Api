package com.mvalu.bettr_api.account_statements

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class AccountStatementDetailSummaryApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: List<StatementSummaryItem>? = null
}