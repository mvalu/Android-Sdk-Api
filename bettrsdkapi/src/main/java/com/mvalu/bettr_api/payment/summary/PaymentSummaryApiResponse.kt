package com.mvalu.bettr_api.payment.summary

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class PaymentSummaryApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: PaymentSummaryResult? = null
}