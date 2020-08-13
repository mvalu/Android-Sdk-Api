package com.mvalu.bettr_api.downloads

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class DocumentDownloadApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: String? = null
}