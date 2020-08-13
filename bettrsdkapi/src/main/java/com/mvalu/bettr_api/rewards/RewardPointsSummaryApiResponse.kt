package com.mvalu.bettr_api.rewards

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class RewardPointsSummaryApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: RewardPointsSummaryResult? = null
}