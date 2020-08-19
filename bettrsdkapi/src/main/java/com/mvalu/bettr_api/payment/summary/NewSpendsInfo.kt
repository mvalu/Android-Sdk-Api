package com.mvalu.bettr_api.payment.summary

import com.squareup.moshi.Json

class NewSpendsInfo {
    @field:Json(name = "title")
    var title: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "amountStatus")
    var amountStatus: String? = null
}