package com.mvalu.bettr_api.emi

import com.squareup.moshi.Json

class ConvertToEmiApiRequest(duration: Int) {

    @field:Json(name = "duration")
    var duration: Int = duration
}