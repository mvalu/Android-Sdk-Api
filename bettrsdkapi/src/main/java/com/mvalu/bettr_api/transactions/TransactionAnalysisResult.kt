package com.mvalu.bettr_api.transactions

import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class TransactionAnalysisResult {
    @field:Json(name = "totalAmountSpend")
    var totalAmountSpend: Double? = null

    @field:Json(name = "totalSpend")
    var totalSpend: Int? = null

    @field:Json(name = "highestSpendValue")
    var highestSpendValue: Double? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    class MerchantCategoryDetail {
        @field:Json(name = "merchantCategory")
        var merchantCategory: String? = null
    }
}