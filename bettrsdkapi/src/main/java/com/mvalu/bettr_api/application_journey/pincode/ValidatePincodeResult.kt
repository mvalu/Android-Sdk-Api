package com.mvalu.bettr_api.application_journey.pincode

import com.mvalu.bettr_api.application_journey.LeadDetail
import com.squareup.moshi.Json

class ValidatePincodeResult {
    @field:Json(name = "pincode")
    var pinCode: String? = null

    @field:Json(name = "city")
    var city: String? = null

    @field:Json(name = "state")
    var state: String? = null

    @field:Json(name = "district")
    var district: String? = null

    @field:Json(name = "status")
    var status: Boolean? = false

    @field:Json(name = "leadObj")
    var leadObj: LeadDetail? = null
}