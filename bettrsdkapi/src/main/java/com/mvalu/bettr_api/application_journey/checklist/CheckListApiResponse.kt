package com.mvalu.bettr_api.application_journey.checklist

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class CheckListApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: CheckListResult? = null
}