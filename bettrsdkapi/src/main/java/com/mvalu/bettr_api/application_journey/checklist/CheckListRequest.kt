package com.mvalu.bettr_api.application_journey.checklist

import com.squareup.moshi.Json

class CheckListRequest {
    @field:Json(name = "productType")
    var productType: String? = null

    @field:Json(name = "leadId")
    var leadId: String? = null
}