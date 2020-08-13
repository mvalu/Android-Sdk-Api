package com.mvalu.bettr_api.rewards

import com.squareup.moshi.Json

class RewardPointInfo {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "point")
    var point: Int? = null

    @field:Json(name = "description")
    var description: String? = null

    @field:Json(name = "redeemedPoint")
    var redeemedPoint: Int? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "LmsCCAccountId")
    var lmsCCAccountId: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null
}