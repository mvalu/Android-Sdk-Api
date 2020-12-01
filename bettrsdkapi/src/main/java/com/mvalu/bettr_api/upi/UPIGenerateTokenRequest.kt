package com.mvalu.bettr_api.upi

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.squareup.moshi.Json

class UPIGenerateTokenRequest : ApiBaseRequest() {
    @field:Json(name = "deviceId")
    var deviceId: String? = null

    @field:Json(name = "simSlotNo")
    var simSlotNo: String? = null
}