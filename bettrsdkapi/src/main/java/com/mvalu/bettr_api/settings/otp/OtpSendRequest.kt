package com.mvalu.bettr_api.settings.otp

import com.squareup.moshi.Json

class OtpSendRequest(userId: String) {

    @field:Json(name = "userId")
    var userId: String? = userId
}