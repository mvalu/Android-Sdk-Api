package com.mvalu.bettr_api.network

import com.squareup.moshi.Json

class CampaignInfo {
    @field:Json(name = "af_cpi")
    var af_cpi: String? = null

    @field:Json(name = "iscache")
    var iscache: String? = null

    @field:Json(name = "click_time")
    var click_time: String? = null

    @field:Json(name = "adset")
    var adset: String? = null

    @field:Json(name = "campaign_id")
    var campaign_id: String? = null

    @field:Json(name = "install_time")
    var install_time: String? = null

    @field:Json(name = "media_source")
    var media_source: String? = null

    @field:Json(name = "agency")
    var agency: String? = null

    @field:Json(name = "af_siteid")
    var af_siteid: String? = null

    @field:Json(name = "af_status")
    var af_status: String? = null

    @field:Json(name = "af_sub1")
    var af_sub1: String? = null

    @field:Json(name = "cost_cents_USD")
    var cost_cents_USD: String? = null

    @field:Json(name = "af_sub5")
    var af_sub5: String? = null

    @field:Json(name = "af_sub4")
    var af_sub4: String? = null

    @field:Json(name = "af_sub3")
    var af_sub3: String? = null

    @field:Json(name = "af_sub2")
    var af_sub2: String? = null

    @field:Json(name = "adset_id")
    var adset_id: String? = null

    @field:Json(name = "campaign")
    var campaign: String? = null

    @field:Json(name = "http_referrer")
    var http_referrer: String? = null

    @field:Json(name = "adgroup")
    var adgroup: String? = null
}