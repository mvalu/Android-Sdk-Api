package com.mvalu.bettr_api.application_journey.checklist

import com.squareup.moshi.Json

class CheckListResult {
    @field:Json(name = "completedDetail")
    var completedDetail: CheckListCompletedDetail? = null

    class CheckListCompletedDetail {
        @field:Json(name = "personalDetail")
        var personalDetail: CheckListCompletedItemDetail? = null

        @field:Json(name = "residentialAddress")
        var residentialDetail: CheckListCompletedItemDetail? = null

        @field:Json(name = "incomeDetail")
        var incomeDetail: CheckListCompletedItemDetail? = null

        @field:Json(name = "incomeDocument")
        var incomeDocument: CheckListCompletedItemDetail? = null

        @field:Json(name = "kycDocument")
        var kycDocument: CheckListCompletedItemDetail? = null

        @field:Json(name = "pennyDropBankAccount")
        var pennyDropBankAccount: CheckListCompletedItemDetail? = null
    }

    class CheckListCompletedItemDetail {
        @field:Json(name = "completedPercentage")
        var completedPercentage: Int? = null

        @field:Json(name = "nextField")
        var nextField: String? = null
    }
}