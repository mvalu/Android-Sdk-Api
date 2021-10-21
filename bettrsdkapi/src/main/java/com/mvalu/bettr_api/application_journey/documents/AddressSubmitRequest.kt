package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json


class AddressSubmitRequest {

    @field:Json(name = "addressLine1")
    var addr1: String? = null

    @field:Json(name = "addressLine2")
    var addr2: String? = null

    @field:Json(name = "city")
    var city: String? = null

    @field:Json(name = "state")
    var state: String? = null

    @field:Json(name = "pincode")
    var pincode: String? = null

    @field:Json(name = "lastStep")
    var lastStep: String? = null
}