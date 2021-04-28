package com.mvalu.bettr_api.emi

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.squareup.moshi.Json

class ConvertToEmiApiRequest(duration: Int) : ApiBaseRequest() {

    @field:Json(name = "duration")
    var duration: Int = duration
}