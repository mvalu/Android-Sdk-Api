package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class RecentMerchantsApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: List<MerchantDetail>? = null
}