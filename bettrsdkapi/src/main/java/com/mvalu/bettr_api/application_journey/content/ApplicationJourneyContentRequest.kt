package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class ApplicationJourneyContentRequest {
    @field:Json(name = "language")
    var language: String? = null //"en||hi"
}