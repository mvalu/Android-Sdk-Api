package com.mvalu.bettr_api.home_module

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class AccountInfoApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: AccountInfo? = null
}