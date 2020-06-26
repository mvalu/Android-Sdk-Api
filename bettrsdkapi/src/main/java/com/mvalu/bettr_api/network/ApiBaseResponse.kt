package com.mvalu.bettr_api.network

import com.squareup.moshi.Json

open class ApiBaseResponse {
    @field:Json(name = "success")
    var success: Boolean? = false

    @field:Json(name = "error")
    var errorResponse: ApiErrorResponse? = null

    @field:Json(name = "msg")
    var message: String? = null
}