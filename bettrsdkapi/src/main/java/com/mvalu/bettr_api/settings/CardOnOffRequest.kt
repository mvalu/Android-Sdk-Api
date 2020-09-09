package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class CardOnOffRequest(action: String) {

    @field:Json(name = "type")
    var action: String = action // "ON || OFF"
}