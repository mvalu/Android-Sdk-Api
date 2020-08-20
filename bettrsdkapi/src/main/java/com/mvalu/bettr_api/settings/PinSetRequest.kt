package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class PinSetRequest {

    @field:Json(name = "pin")
    var pin: String? = null

    @field:Json(name = "pin_set_token")
    var pinSetToken: String? = null

    @field:Json(name = "otp")
    var otp: String? = null
}