package com.mvalu.bettr_api.application_journey.documents

import com.mvalu.bettr_api.home_module.AccountInfo
import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class AadharKycResponse : ApiBaseResponse() {

    @field:Json(name = "results")
    var result: Result? = null

    class Result {
        @field:Json(name = "accountInfo")
        var accountInfo: AccountInfo? = null
    }
}