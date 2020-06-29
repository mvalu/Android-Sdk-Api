package com.mvalu.bettr_api.transactions

import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class CardTransactionsResults {
    @field:Json(name = "docs")
    var transactions: List<CardTransactionDetails>? = null

    @field:Json(name = "offset")
    var offset: Int? = null

    @field:Json(name = "limit")
    var limit: Int? = null

    @field:Json(name = "total")
    var total: Int? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null
}