package com.mvalu.bettr_api.transactions

import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class TransactionAnalysisResult {
    @field:Json(name = "totalAmountSpend")
    var totalAmountSpend: Double? = null

    @field:Json(name = "totalSpend")
    var totalSpend: Double? = null

    @field:Json(name = "highestSpendValue")
    var highestSpendValue: Double? = null

    @field:Json(name = "categoryWise")
    var categoryWise: List<MerchantCategoryDetail>? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    class MerchantCategoryDetail {
        @field:Json(name = "merchantCategory")
        var merchantCategory: String? = null

        @field:Json(name = "sumCategory")
        var sumCategory: Double? = null

        @field:Json(name = "countCategory")
        var countCategory: Int? = null

        @field:Json(name = "maxAmount")
        var maxAmount: Double? = null
    }
}