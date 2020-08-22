package com.mvalu.bettr_api.payment.detail

import com.squareup.moshi.Json

class PaymentDetailResult {
    @field:Json(name = "payment")
    var payment: Payment? = null

    @field:Json(name = "card")
    var card: Card? = null

    class Payment {
        @field:Json(name = "id")
        var id: String? = null

        @field:Json(name = "amount")
        var amount: Double? = null

        @field:Json(name = "source")
        var source: String? = null

        @field:Json(name = "status")
        var status: String? = null

        @field:Json(name = "createdAt")
        var createdAt: String? = null

        @field:Json(name = "updatedAt")
        var updatedAt: String? = null
    }

    class Card {
        @field:Json(name = "cardNumber")
        var cardNumber: String? = null
    }
}