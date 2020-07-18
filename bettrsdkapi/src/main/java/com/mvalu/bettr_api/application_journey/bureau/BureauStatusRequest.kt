package com.mvalu.bettr_api.application_journey.bureau

import com.squareup.moshi.Json

class BureauStatusRequest {
    @field:Json(name = "userId")
    var userId: String? = null

    @field:Json(name = "leadId")
    var leadId: String? = null
}