package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class UPISetUpApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: UPISetUpResult? = null
}