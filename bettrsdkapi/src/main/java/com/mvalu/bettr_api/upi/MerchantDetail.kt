package com.mvalu.bettr_api.upi

import com.squareup.moshi.Json

class MerchantDetail {
    @field:Json(name = "merchantName")
    var merchantName: String? = null

    @field:Json(name = "acceptedName")
    var acceptedName: String? = null

    @field:Json(name = "merchantVpa")
    var merchantVpa: String? = null

    @field:Json(name = "upiMerchantId")
    var upiMerchantId: String? = null

    @field:Json(name = "category")
    var category: String? = null
}