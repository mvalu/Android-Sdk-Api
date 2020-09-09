package com.mvalu.bettr_api.settings.plastic_card

import com.squareup.moshi.Json

class CardNumberVerifyRequest(cardNumber: Int) {

    @field:Json(name = "cardNumber")
    var cardNumber: Int = cardNumber
}