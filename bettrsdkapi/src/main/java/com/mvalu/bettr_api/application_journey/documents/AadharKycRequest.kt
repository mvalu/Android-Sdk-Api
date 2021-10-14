package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json

class AadharKycRequest(passcode: String?, aadhaarFileUrl: String?, aadharData: String?) {

    @field:Json(name = "leadId")
    var leadId: String? = null

    @field:Json(name = "userId")
    var userId: String? = null

    @field:Json(name = "passcode")
    var passcode: String? = passcode

    @field:Json(name = "aadhaar")
    var aadhaarFileUrl: String? = aadhaarFileUrl //s3 url of uploaded file on our server

    @field:Json(name = "aadharData")
    var aadharData: String? = aadharData
}