package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.squareup.moshi.Json

class UPIPaymentRequest : ApiBaseRequest() {
    @field:Json(name = "upiMerchantId")
    var upiMerchantId: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null
}