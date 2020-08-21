package com.mvalu.bettr_api.settings.otp

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class OtpApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: OtpResult? = null
}