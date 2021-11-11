package com.mvalu.bettr_api.payment.summary

import com.squareup.moshi.Json

class PaymentInfo {
    @field:Json(name = "title")
    var title: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "amountStatus")
    var amountStatus: String? = null

    @field:Json(name = "paymentbreakUp")
    var breakUp: List<Item>? = null

    class Item {
        @field:Json(name = "paymentDate")
        var date: String? = null

        @field:Json(name = "description")
        var description: String? = null

        @field:Json(name = "amountStatus")
        var amountStatus: String? = null

        @field:Json(name = "amount")
        var amount: Double? = null
    }
}