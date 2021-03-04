package com.mvalu.bettr_api

import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class BankAccountIFSCResponseModel : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: List<IFSC>? = null

    class IFSC {
        @field:Json(name = "IFSC")
        var ifsc: String? = null

        @field:Json(name = "value")
        var value: String? = null
    }
}