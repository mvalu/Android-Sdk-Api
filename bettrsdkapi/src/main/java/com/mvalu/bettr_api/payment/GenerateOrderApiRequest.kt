package com.mvalu.bettr_api.payment

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.squareup.moshi.Json

class GenerateOrderApiRequest : ApiBaseRequest() {
    @field:Json(name = "amount")
    var amount: Double? = null
}