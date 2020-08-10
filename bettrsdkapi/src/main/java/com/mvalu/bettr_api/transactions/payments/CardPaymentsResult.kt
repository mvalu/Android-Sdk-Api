package com.mvalu.bettr_api.transactions.payments

import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class CardPaymentsResult {

    @field:Json(name = "docs")
    var paymentsList: List<PaymentInfo>? = null

    @field:Json(name = "offset")
    var offset: Int? = null

    @field:Json(name = "limit")
    var limit: Int? = null

    @field:Json(name = "total")
    var total: Int? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null
}