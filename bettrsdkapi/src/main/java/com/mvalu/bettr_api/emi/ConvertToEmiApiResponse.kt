package com.mvalu.bettr_api.emi

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class ConvertToEmiApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: EmiInfo? = null
}