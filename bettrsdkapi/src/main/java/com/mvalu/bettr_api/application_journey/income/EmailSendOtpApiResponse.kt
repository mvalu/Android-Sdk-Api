package com.mvalu.bettr_api.application_journey.income

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class EmailSendOtpApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: Result? = null

    class Result {
        @field:Json(name = "id")
        var otpId: String? = null
    }
}