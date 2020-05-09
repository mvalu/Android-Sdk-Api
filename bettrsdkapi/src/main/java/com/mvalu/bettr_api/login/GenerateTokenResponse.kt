package com.mvalu.bettr_api.login

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class GenerateTokenResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var result: Result? = null

    class Result {
        @field:Json(name = "token")
        var token: String? = null
    }
}