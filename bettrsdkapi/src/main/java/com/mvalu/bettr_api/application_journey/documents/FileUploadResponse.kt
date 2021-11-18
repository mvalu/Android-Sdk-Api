package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class FileUploadResponse: ApiBaseResponse() {
    @field:Json(name = "results")
    var result: Result? = null

    class Result {
        @field:Json(name = "fileUrl")
        var fileUrl: String? = null
    }
}