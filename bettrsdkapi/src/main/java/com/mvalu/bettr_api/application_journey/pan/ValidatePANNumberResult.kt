package com.mvalu.bettr_api.application_journey.pan

import com.mvalu.bettr_api.application_journey.LeadDetail
import com.squareup.moshi.Json

class ValidatePANNumberResult {
    @field:Json(name = "data")
    var data: Data? = null

    @field:Json(name = "status")
    var status: Boolean = false

    @field:Json(name = "leadDetail")
    var leadDetail: LeadDetail? = null

    @field:Json(name = "namematchscore")
    var namematchscore: Int? = 0

    class Data {
        @field:Json(name = "pan_number")
        var panNumber: String? = null

        @field:Json(name = "name")
        var name: String? = null

        @field:Json(name = "pan_status")
        var panStatus: String? = null

        //error related fields
        @field:Json(name = "statusCode")
        var statusCode: Int = 0

        @field:Json(name = "message")
        var message: String? = null
    }
}