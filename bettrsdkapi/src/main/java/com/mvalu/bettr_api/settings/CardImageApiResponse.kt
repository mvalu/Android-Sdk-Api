package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class CardImageApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: CardImageResult? = null

    class CardImageResult {

        @field:Json(name = "url")
        var url: String? = null//Full image url. Valid for 15 mins only

        @field:Json(name = "label")
        var label: String? = null//label to be shown above card image, if null use default value harcoded on the app.

    }
}