package com.mvalu.bettr_api.search

import com.squareup.moshi.Json

class SearchFieldDataItem {
    @field:Json(name = "value")
    var value: String? = null
}