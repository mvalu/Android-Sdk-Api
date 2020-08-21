package com.mvalu.bettr_api.payment.detail

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class PaymentDetailApiResponse:ApiBaseResponse() {
    @field:Json(name = "results")
    var results: PaymentDetailResult? = null
}