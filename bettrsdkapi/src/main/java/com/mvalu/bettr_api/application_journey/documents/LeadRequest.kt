package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json

class LeadRequest {
    @field:Json(name = "leadId")
    var leadId: String? = null
}