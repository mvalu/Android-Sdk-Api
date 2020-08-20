package com.mvalu.bettr_api.payment

import com.squareup.moshi.Json

class PaymentStatusResult {
    @field:Json(name = "status")
    var status: String? = null
}