package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class CardImageApiResponse {

    @field:Json(name = "results")
    var results: String? = null //Full image url. Valid for 15 mins only
}