package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.squareup.moshi.Json

class UPISetUpRequest : ApiBaseRequest() {
    @field:Json(name = "tokenId")
    var tokenId: String? = null

    @field:Json(name = "appVersion")
    var appVersion: String? = null

    @field:Json(name = "deviceApp")
    var deviceApp: String? = null

    @field:Json(name = "deviceModel")
    var deviceModel: String? = null

    @field:Json(name = "deviceOs")
    var deviceOs: String? = null

    @field:Json(name = "deviceType")
    var deviceType: String? = null
}