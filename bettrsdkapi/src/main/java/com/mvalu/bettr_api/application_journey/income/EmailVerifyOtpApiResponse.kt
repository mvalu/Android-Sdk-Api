package com.mvalu.bettr_api.application_journey.income

import com.mvalu.bettr_api.card_user.UserDetail
import com.mvalu.bettr_api.network.ApiBaseResponse
import com.squareup.moshi.Json

class EmailVerifyOtpApiResponse : ApiBaseResponse() {
    @field:Json(name = "results")
    var results: Result? = null

    class Result {
        @field:Json(name = "id")
        var otpId: String? = null

        @field:Json(name = "userDetails")
        var userDetail: UserDetail? = null
    }
}