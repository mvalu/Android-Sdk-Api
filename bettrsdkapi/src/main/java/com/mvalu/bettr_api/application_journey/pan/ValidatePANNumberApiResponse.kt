package com.mvalu.bettr_api.application_journey.pan

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class ValidatePANNumberApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: ValidatePANNumberResult? = null
}