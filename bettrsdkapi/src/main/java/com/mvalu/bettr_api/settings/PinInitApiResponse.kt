package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class PinInitApiResponse {

    @field:Json(name = "results")
    var results: PinInitResult? = null
}