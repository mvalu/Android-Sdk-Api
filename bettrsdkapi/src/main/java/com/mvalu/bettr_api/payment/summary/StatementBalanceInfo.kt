package com.mvalu.bettr_api.payment.summary

import com.squareup.moshi.Json

class StatementBalanceInfo {
    @field:Json(name = "title")
    var title: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "amountStatus")
    var amountStatus: String? = null

    @field:Json(name = "breakUp")
    var breakUp: List<Item>? = null

    class Item {
        @field:Json(name = "startDate")
        var startDate: String? = null

        @field:Json(name = "endDate")
        var endDate: String? = null

        @field:Json(name = "paymentDate")
        var paymentDate: String? = null

        @field:Json(name = "amountStatus")
        var amountStatus: String? = null

        @field:Json(name = "amount")
        var amount: Double? = null
    }
}