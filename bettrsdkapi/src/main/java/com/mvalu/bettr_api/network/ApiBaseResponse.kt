package com.mvalu.bettr_api.network

import com.squareup.moshi.Json

open class ApiBaseResponse {
    @field:Json(name = "name")
    var name: String? = null

    @field:Json(name = "message")
    var message: String? = null

    @field:Json(name = "stack")
    var stack: String? = null
}