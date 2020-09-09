package com.mvalu.bettr_api.settings.otp

import com.squareup.moshi.Json

class OtpSendRequest(userId: String, purpose: String? = null) {

    @field:Json(name = "userId")
    var userId: String? = userId

    @field:Json(name = "purpose")
    var purpose: String? = purpose //optional param: just add simple description of api use into this like, "for card pin set" or "for card block" etc
}