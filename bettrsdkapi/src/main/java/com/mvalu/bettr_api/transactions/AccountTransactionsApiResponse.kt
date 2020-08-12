package com.mvalu.bettr_api.transactions

import com.squareup.moshi.Json

class AccountTransactionsApiResponse {
    @field:Json(name = "results")
    var results: AccountTransactionsResults? = null
}