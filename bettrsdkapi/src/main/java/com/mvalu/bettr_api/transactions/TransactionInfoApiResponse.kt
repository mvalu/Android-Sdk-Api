package com.mvalu.bettr_api.transactions

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class TransactionInfoApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: TransactionInfo? = null
}