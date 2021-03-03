package com.mvalu.bettr_api.application_journey

import com.squareup.moshi.Json

class BranchDetail {
    @field:Json(name = "value")
    var value: String? = null

    @field:Json(name = "BANK")
    var bank: String? = null

    @field:Json(name = "IFSC")
    var ifsc: String? = null

    @field:Json(name = "BRANCH")
    var branch: String? = null

    @field:Json(name = "CITY")
    var city: String? = null

    @field:Json(name = "key")
    var key: String? = null
}