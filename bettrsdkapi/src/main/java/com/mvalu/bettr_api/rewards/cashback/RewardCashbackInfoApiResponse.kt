package com.mvalu.bettr_api.rewards.cashback

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class RewardCashbackInfoApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: RewardCashbackInfo? = null
}