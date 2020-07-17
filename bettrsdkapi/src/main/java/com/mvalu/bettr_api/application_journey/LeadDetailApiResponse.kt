package com.mvalu.bettr_api.application_journey

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class LeadDetailApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: LeadDetail? = null
}