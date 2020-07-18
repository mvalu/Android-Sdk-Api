package com.mvalu.bettr_api.application_journey.pincode

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class ValidatePincodeApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: ValidatePincodeResult? = null
}