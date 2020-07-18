package com.mvalu.bettr_api.application_journey.bureau

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class BureauQuestionApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: BureauQuestionResult? = null
}