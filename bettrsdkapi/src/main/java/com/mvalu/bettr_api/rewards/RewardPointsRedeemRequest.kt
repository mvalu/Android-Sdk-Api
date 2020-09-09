package com.mvalu.bettr_api.rewards

import com.squareup.moshi.Json

class RewardPointsRedeemRequest(points: Int) {

    @field:Json(name = "point")
    var points: Int = points
}