package com.mvalu.bettr_api.rewards

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object Rewards : ApiSdkBase() {
    private const val TAG = "DownloadDoc"
    private var rewardPointsCallback: ApiResponseCallback<RewardPointsResult>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getRewardPoints(
        rewardPointsCallback: ApiResponseCallback<RewardPointsResult>,
        accountId: String,
        startMonth: String?,
        endMonth: String?,
        pointStart: String?,
        pointEnd: String?,
        startDate: Int?,
        endDate: Int?,
        search: String?
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.rewardPointsCallback = rewardPointsCallback
        callApi(
            serviceApi.getRewardPoints(
                BettrApiSdk.getOrganizationId(),
                accountId,
                startMonth, endMonth, pointStart, pointEnd, startDate, endDate, search
            ), ApiTag.REWARD_POINTS_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "reward points fetched")
                val rewardPointsApiResponse = response as RewardPointsApiResponse
                rewardPointsCallback?.onSuccess(rewardPointsApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}