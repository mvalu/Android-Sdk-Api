package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class VerifyDocumentsApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: VerifyDocumentsResult? = null
}