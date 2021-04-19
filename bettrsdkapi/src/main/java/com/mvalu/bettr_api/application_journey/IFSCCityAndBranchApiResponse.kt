package com.mvalu.bettr_api.application_journey

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class IFSCCityAndBranchApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: List<BranchDetail>? = null
}