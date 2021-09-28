package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.application_journey.LeadDetail
import com.squareup.moshi.Json

class VerifyDocumentsResult {
    @field:Json(name = "status")
    var status: Status? = null

    @field:Json(name = "leadUpdateFields")
    var leadUpdateFields: VerifiedDocumentsDetails? = null

    @field:Json(name = "nextStep")
    var nextStep: Boolean = false

    @field:Json(name = "error")
    var error: String? = null

    @field:Json(name = "leadObj")
    var leadObj: LeadDetail? = null

    class VerifiedDocumentsDetails {
        @field:Json(name = "pan")
        var pan: String? = null

        @field:Json(name = "photo")
        var photo: String? = null

        @field:Json(name = "aadharFront")
        var aadharFront: String? = null

        @field:Json(name = "aadharBack")
        var aadharBack: String? = null

        @field:Json(name = "panValidateId")
        var panValidateId: String? = null

        @field:Json(name = "aadharValidateId")
        var aadharValidateId: String? = null

        @field:Json(name = "photoValidateId")
        var photoValidateId: String? = null

        @field:Json(name = "aadharFaceCheckId")
        var aadharFaceCheckId: String? = null

        @field:Json(name = "panFaceCheckId")
        var panFaceCheckId: String? = null
    }

    class Status {
        @field:Json(name = "aadhar")
        var aadhar: DocData? = null

        @field:Json(name = "pan")
        var pan: DocData? = null

        @field:Json(name = "photo")
        var photo: DocData? = null
    }

    class DocData {
        @field:Json(name = "valid")
        var valid: Boolean = false

        @field:Json(name = "error")
        var error: String? = null
    }
}