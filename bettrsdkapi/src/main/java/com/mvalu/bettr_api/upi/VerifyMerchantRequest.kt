package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.squareup.moshi.Json

class VerifyMerchantRequest : ApiBaseRequest() {
    @field:Json(name = "merchantVpa")
    var merchantVpa: String? = null
}