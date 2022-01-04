package com.mvalu.bettr_api.payment.summary

import com.squareup.moshi.Json

class PaymentSummaryResult {
    @field:Json(name = "dueInfo")
    var dueInfo: PaymentDueInfo? = null

    @field:Json(name = "newSpendsInfo")
    var newSpendsInfo: NewSpendsInfo? = null

    @field:Json(name = "statementBalanceInfo")
    var statementBalanceInfo: StatementBalanceInfo? = null

    @field:Json(name = "lastStementInfo")
    var lastStementInfo: LastSatementInfo? = null

    @field:Json(name = "fineInfo")
    var fineInfo: FineInfo? = null

    @field:Json(name = "unbilledInfo")
    var unbilledInfo: UnbilledInfo? = null

    @field:Json(name = "paymentInfo")
    var paymentInfo: PaymentInfo? = null

    @field:Json(name = "cashbackInfo")
    var cashbackInfo: CashbackInfo? = null

}