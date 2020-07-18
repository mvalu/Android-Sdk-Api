package com.mvalu.bettr_api.application_journey.bureau

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class BureauStatusApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: BureauStatusResult? = null
}