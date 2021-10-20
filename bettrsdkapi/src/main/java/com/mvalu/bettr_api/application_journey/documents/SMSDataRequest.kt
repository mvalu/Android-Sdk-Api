package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json

class SMSDataRequest {

    @field:Json(name = "userSmsList")
    var userSmsList: List<Request>? = null

    class Request {
        @field:Json(name = "sender")
        var sender: String? = null

        @field:Json(name = "smsContent")
        var smsContent: String? = null

        @field:Json(name = "date")
        var date: String? = null
    }
}