package com.mvalu.bettr_api.settings.otp

import com.squareup.moshi.Json

class OtpResult {

    @field:Json(name = "otpRequestId")
    var otpRequestId: String? = null

    @field:Json(name = "status")
    var status: Boolean? = null //This param is only for OTP resend result and OTP verification result.
}