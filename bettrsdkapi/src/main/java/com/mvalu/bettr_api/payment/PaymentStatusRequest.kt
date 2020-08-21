package com.mvalu.bettr_api.payment

import com.squareup.moshi.Json

class PaymentStatusRequest {
    @field:Json(name = "paymentId")
    var paymentId: String? = null

    @field:Json(name = "orderId")
    var orderId: String? = null
}