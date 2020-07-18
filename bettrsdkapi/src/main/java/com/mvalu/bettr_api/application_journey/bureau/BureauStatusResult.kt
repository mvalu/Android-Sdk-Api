package com.mvalu.bettr_api.application_journey.bureau

import com.squareup.moshi.Json

class BureauStatusResult {
    @field:Json(name = "applicationId")
    var applicationId: String? = null

    @field:Json(name = "bureauApplicationParserId")
    var bureauApplicationParserId: String? = null

    @field:Json(name = "status")
    var status: BureauStatus? = null

    @field:Json(name = "scoreLessThan300")
    var scoreLessThan300: Boolean = false

    enum class BureauStatus {
        @Json(name = "SUCCESS")
        SUCCESS,
        @Json(name = "QUESTION")
        QUESTION,
        @Json(name = "FAILURE")
        FAILURE,
        @Json(name = "NOHIT")
        NOHIT,
        @Json(name = "BUREAU_DOWN")
        BUREAU_DOWN
    }
}