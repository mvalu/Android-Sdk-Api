package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json

class DocumentUploadResult {
    @field:Json(name = "fileUrl")
    var fileUrl: String? = null
}