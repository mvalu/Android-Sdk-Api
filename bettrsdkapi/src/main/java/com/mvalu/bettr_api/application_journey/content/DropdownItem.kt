package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class DropdownItem {
    @field:Json(name = "displayName")
    var displayName: String? = null

    @field:Json(name = "value")
    var value: String? = null
}