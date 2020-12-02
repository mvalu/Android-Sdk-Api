package com.mvalu.bettr_api.upi

import com.squareup.moshi.Json

class UPIPaymentResult {
    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "transactionId")
    var transactionId: String? = null

    @field:Json(name = "errorMessage")
    var errorMessage: String? = null
}