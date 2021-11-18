package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json

class ConfirmAdharAddrsRequest {
    @field:Json(name = "aadharAddressAs")
    var aadharAddrsAs: String? = null

    @field:Json(name = "lastStep")
    var lastStep: String? = null
}