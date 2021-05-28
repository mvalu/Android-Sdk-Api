package com.mvalu.bettr_api.application_journey.income

import com.squareup.moshi.Json

class EmailResendOtpRequest(otpId: String) {

    @field:Json(name = "id")
    var otpId: String? = otpId
}