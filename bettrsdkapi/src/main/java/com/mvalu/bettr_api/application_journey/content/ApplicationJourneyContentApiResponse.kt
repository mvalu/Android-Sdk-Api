package com.mvalu.bettr_api.application_journey.content

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class ApplicationJourneyContentApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: ApplicationJourneyContentResult? = null
}