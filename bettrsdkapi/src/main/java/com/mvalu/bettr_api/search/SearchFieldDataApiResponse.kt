package com.mvalu.bettr_api.search

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class SearchFieldDataApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var result: List<SearchFieldDataItem>? = null
}