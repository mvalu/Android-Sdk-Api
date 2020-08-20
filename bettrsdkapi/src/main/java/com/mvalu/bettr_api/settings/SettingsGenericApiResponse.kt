package com.mvalu.bettr_api.settings

import com.squareup.moshi.Json

class SettingsGenericApiResponse {

    @field:Json(name = "results")
    var results: Boolean? = false
}