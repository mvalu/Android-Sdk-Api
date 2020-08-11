package com.mvalu.bettr_api.home_module.statement

import com.mvalu.bettr_api.home_module.HomeModuleResults
import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class HomeModuleStatementApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: List<HomeModuleStatementResults>? = null
}