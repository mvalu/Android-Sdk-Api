package com.mvalu.bettr_api.application_journey.checklist

import com.squareup.moshi.Json

class CheckListResult {
    @field:Json(name = "completedDetail")
    var completedDetail: CheckListCompletedDetail? = null

    class CheckListCompletedDetail {
        @field:Json(name = "personalDetail")
        var personalDetail: CheckListCompletedItemDetail? = null

        @field:Json(name = "residentialDetail")
        var residentialDetail: CheckListCompletedItemDetail? = null

        @field:Json(name = "incomelDetail")
        var incomelDetail: CheckListCompletedItemDetail? = null

        @field:Json(name = "kycDocument")
        var kycDocument: CheckListCompletedItemDetail? = null

        @field:Json(name = "bankVerification")
        var bankVerification: CheckListCompletedItemDetail? = null
    }

    class CheckListCompletedItemDetail {
        @field:Json(name = "completedPercentage")
        var completedPercentage: Int? = null

        @field:Json(name = "nextField")
        var nextField: String? = null
    }
}