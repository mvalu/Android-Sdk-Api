package com.mvalu.bettr_api.application_journey.documents

import com.squareup.moshi.Json

class VerifyDocumentsRequest {
    @field:Json(name = "productType")
    var productType: String? = null

    @field:Json(name = "leadId")
    var leadId: String? = null

    @field:Json(name = "userId")
    var userId: String? = null

    @field:Json(name = "pan")
    var pan: String? = null

    @field:Json(name = "photo")
    var photo: String? = null

    @field:Json(name = "aadharFront")
    var aadharFront: String? = null

    @field:Json(name = "aadharBack")
    var aadharBack: String? = null

    @field:Json(name = "docType")
    var docType: String? = null

}