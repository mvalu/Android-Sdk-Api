package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class CardImageResult {

    @field:Json(name = "url")
    var url: String? = null//Full image url. Valid for 15 mins only

    @field:Json(name = "label")
    var label: String? = null//label to be shown above card image, if null use default value harcoded on the app.

}