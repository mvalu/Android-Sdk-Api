package com.mvalu.bettr_api.application_journey

import com.squareup.moshi.Json

class ValidatePANNumberRequest {

    @field:Json(name = "pan")
    var pan: String? = null

    @field:Json(name = "name")
    var name: String? = null

}