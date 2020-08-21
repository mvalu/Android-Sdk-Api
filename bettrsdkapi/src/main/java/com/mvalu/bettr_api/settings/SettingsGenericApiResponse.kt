package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class SettingsGenericApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: Boolean? = false
}