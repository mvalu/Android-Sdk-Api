package com.mvalu.bettr_api.application_journey.pincode

import com.squareup.moshi.Json

class ValidatePincodeRequest {

    @field:Json(name = "userId")
    var userId: String? = null

    @field:Json(name = "pincode")
    var pincode: String? = null
}