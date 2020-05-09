package com.mvalu.bettr_api.login

import com.mvalu.bettr_api.network.ApiBaseRequest
import com.mvalu.bettr_api.network.CampaignInfo
import com.mvalu.bettr_api.network.DeviceInfo
import com.squareup.moshi.Json

class GenerateTokenRequest : ApiBaseRequest() {

    @field:Json(name = "marketCampaign")
    var marketCampaign: CampaignInfo? = null

    @field:Json(name = "loginType")
    var loginType: String = "CUSTOMER"

    @field:Json(name = "signUpRole")
    var signUpRole: String = "CUSTOMER"

    @field:Json(name = "loginRoles")
    var loginRoles: List<String> = arrayListOf("CUSTOMER")

    @field:Json(name = "mobileNumber")
    var mobileNumber: String? = null

    @field:Json(name = "imei")
    var imei: String? = null

    @field:Json(name = "userAgent")
    var deviceInfo: DeviceInfo? = DeviceInfo()
}