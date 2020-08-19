package com.mvalu.bettr_api.payment.summary

import com.squareup.moshi.Json

class DueInfo {
    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "statusText")
    var statusText: String? = null

    @field:Json(name = "totalDue")
    var totalDue: Double? = null

    @field:Json(name = "minDue")
    var minDue: Double? = null

    @field:Json(name = "interestRateForRemainingAmount")
    var interestRateForRemainingAmount: Double? = null

    @field:Json(name = "title")
    var title: String? = null
}