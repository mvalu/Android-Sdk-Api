package com.mvalu.bettr_api.card_user

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class CardUserResponse:ApiBaseResponse() {
    @field:Json(name = "results")
    var result: UserDetail? = null
}