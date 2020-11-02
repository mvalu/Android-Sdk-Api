package com.mvalu.bettr_api.login

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.network.CampaignInfo
import com.mvalu.bettr_api.network.DeviceInfo

class TokenGeneration private constructor(
    val USER_ID: String,
    val campaignInfo: CampaignInfo?,
    val deviceInfo: DeviceInfo
) : ApiSdkBase() {
    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun generateAccessToken() {

    }

    class Builder {
//        var USER_ID: String,
//        var campaignInfo: CampaignInfo?,
//        var deviceInfo: DeviceInfo
//
//        fun accesskey(accessKey: String) = apply { this.ACCESS_KEY = accessKey }
//        fun secretkey(secretKey: String) = apply { this.SECRET_KEY = secretKey }
//        fun orgnizationId(orgnizationId: String) = apply { this.ORGANIZATION_ID = orgnizationId }
//        fun userId(userId: String) = apply { this.USER_ID = userId }
//        fun campaignInfo(campaignInfo: CampaignInfo?) = apply { this.campaignInfo = campaignInfo }
//        fun deviceInfo(deviceInfo: DeviceInfo) = apply { this.deviceInfo = deviceInfo }
//        fun build() = TokenGeneration(ACCESS_KEY, SECRET_KEY,
//            ORGANIZATION_ID, USER_ID,
//            campaignInfo, deviceInfo)
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {

    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {

    }

    override fun onTimeout(apiTag: ApiTag) {

    }

    override fun onNetworkError(apiTag: ApiTag) {

    }

    override fun onAuthError(apiTag: ApiTag) {

    }
}