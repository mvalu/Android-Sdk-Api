package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.home_module.CardInfo
import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class BlockCardApiResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var results: CardInfo? = null
}