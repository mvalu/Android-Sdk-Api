package com.mvalu.bettr_api.payment

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class PaymentStatusApiResponse:ApiBaseResponse() {
    @field:Json(name = "results")
    var results: PaymentStatusResult? = null
}