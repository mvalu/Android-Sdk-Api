package com.mvalu.bettr_api.rewards.cashback

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class RewardCashbackApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: RewardCashbackResult? = null
}