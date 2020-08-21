package com.mvalu.bettr_api.settings.otp

import com.squareup.moshi.Json

class OtpReSendRequest(otpRequestId: String) {

    @field:Json(name = "otpRequestId")
    var otpRequestId: String? = otpRequestId
}