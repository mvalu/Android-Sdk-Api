package com.mvalu.bettr_api.settings.otp

import com.squareup.moshi.Json

class OtpVerifyRequest(otpRequestId: String, otp: Int) {

    @field:Json(name = "otpRequestId")
    var otpRequestId: String? = otpRequestId

    @field:Json(name = "otp")
    var otp: Int? = otp
}