package com.mvalu.bettr_api.rewards

import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class RewardPointsSummaryResult {
    @field:Json(name = "totalPointReceived")
    var totalPointReceived: Int? = null

    @field:Json(name = "totalBalanceRedeemed")
    var totalBalanceRedeemed: Double? = null

    @field:Json(name = "totalBalancePoint")
    var totalBalancePoint: Int? = null

    @field:Json(name = "conversionRatio")
    var conversionRatio: Double? = null

    @field:Json(name = "conversionRatioText")
    var conversionRatioText: String? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null
}