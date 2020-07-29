package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class ApplicationJourneyContentRequest {
    @field:Json(name = "language")
    var language: String = "en" //"en||hi"

    @field:Json(name = "placeholder")
    var placeholder: String =
        "pincode_section_end_cc,personal_details_start_cc,personal_details_end_cc,residential_details_start_cc,income_details_start_cc,income_details_end_cc,business_docs_submit_start_cc,business_docs_submit_end_cc,kyc_docs_start_cc,kyc_docs_end_cc,bank_details_start_cc,bank_details_end_cc,nach_end_cc,aadhar_ekyc_start_cc,application_submitted_cc,lead_success_cc,lead_reject_cc"
}