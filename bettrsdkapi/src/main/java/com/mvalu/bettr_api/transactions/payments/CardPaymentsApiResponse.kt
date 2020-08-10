package com.mvalu.bettr_api.transactions.payments

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class CardPaymentsApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: CardPaymentsResult? = null
}