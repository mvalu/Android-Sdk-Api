package com.mvalu.bettr_api.application_journey.income

import com.squareup.moshi.Json

class EmailVerifyOtpRequest(otpId: String, otp: Int) {

    @field:Json(name = "id")
    var otpId: String? = otpId

    @field:Json(name = "otp")
    var otp: Int? = otp
}