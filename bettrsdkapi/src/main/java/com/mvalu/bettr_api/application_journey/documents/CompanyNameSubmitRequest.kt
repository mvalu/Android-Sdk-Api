package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json


class CompanyNameSubmitRequest {

    @field:Json(name = "employerName")
    var employerName: String? = null

    @field:Json(name = "lastStep")
    var lastStep: String? = null
}