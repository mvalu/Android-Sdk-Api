package com.mvalu.bettr_api.settings.plastic_card

import com.squareup.moshi.Json

class CardActivationRequest(pin: String) {

    @field:Json(name = "pin")
    var pin: String? = pin
}