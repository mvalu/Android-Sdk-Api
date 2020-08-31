package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class PinSetRequest(pin: String) {

    @field:Json(name = "pin")
    var pin: String? = pin
}