package com.mvalu.bettr_api.payment.summary

import com.squareup.moshi.Json

class UnbilledInfo {
    @field:Json(name = "title")
    var title: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "amountStatus")
    var amountStatus: String? = null

    @field:Json(name = "finebreakUp")
    var breakUp: List<Item>? = null

    class Item {
        @field:Json(name = "date")
        var date: String? = null

        @field:Json(name = "description")
        var description: String? = null

        @field:Json(name = "amountStatus")
        var amountStatus: String? = null

        @field:Json(name = "amount")
        var amount: Double? = null
    }
}