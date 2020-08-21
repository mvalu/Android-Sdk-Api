package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class CardImageApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: String? = null //Full image url. Valid for 15 mins only
}