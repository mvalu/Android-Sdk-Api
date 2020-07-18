package com.mvalu.bettr_api.application_journey.pan

import com.squareup.moshi.Json

class ValidatePANNumberRequest {

    @field:Json(name = "pan")
    var pan: String? = null

    @field:Json(name = "name")
    var name: String? = null

}