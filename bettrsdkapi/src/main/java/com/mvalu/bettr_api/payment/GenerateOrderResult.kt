package com.mvalu.bettr_api.payment

import com.squareup.moshi.Json

class GenerateOrderResult {
    @field:Json(name = "razorpayCustomerId")
    var razorpayCustomerId: String? = null

    @field:Json(name = "razorpayOrderId")
    var razorpayOrderId: String? = null
}