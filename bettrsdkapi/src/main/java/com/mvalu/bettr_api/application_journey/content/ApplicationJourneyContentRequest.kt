package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class ApplicationJourneyContentRequest {
    @field:Json(name = "language")
    var language: String = "en" //"en||hi"

    @field:Json(name = "placeholder")
    var placeholder: String =
        "bureau_cc_consent,location_cc_consent,job_detail_cc_consent,payment_cc_consent,account_info_cc_consent,document_submission_cc_consent,reject_message_cc"
}