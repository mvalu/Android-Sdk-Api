package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class CardImageApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: CardImageResult? = null

}