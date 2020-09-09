package com.mvalu.bettr_api.rewards.cashback

import com.squareup.moshi.Json

class RewardCashbackInfo {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "conversionRatio")
    var conversionRatio: Float? = null

    @field:Json(name = "description")
    var description: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null

    @field:Json(name = "LmsCCAccountId")
    var lmsCCAccountId: String? = null

    @field:Json(name = "LmsCCRewardRedeemedId")
    var lmsCCRewardRedeemedId: String? = null
}