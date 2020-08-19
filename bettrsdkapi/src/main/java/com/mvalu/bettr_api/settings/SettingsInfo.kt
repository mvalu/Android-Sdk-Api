package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.home_module.AccountInfo
import com.mvalu.bettr_api.home_module.CardInfo
import com.mvalu.bettr_api.home_module.DueInfo
import com.squareup.moshi.Json

class SettingsInfo {

    @field:Json(name = "dueInfo")
    var dueInfo: DueInfo? = null

    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    @field:Json(name = "cardInfo")
    var cardInfo: CardInfo? = null
}