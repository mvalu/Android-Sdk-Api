package com.mvalu.bettr_api.application_journey.income

import com.squareup.moshi.Json

class EmailSendOtpRequest (email: String, type: String) {

    @field:Json(name = "email")
    var email: String? = email

    @field:Json(name = "type")
    var type: String? = type //EmailType.OFFICIAL or personal
}