package com.mvalu.bettr_api.application_journey.bureau

import com.squareup.moshi.Json

class BureauQuestionRequest {
    @field:Json(name = "userId")
    var userId: String? = null

    @field:Json(name = "applicationId")
    var applicationId: String? = null

    @field:Json(name = "leadId")
    var leadId: String? = null
}