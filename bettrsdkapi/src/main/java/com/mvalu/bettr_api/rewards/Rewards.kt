package com.mvalu.bettr_api.rewards

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackApiResponse
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackResult
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object Rewards : ApiSdkBase() {
    private const val TAG = "Rewards"
    private var rewardPointsCallback: ApiResponseCallback<RewardPointsResult>? = null
    private var rewardCashbacksCallback: ApiResponseCallback<RewardCashbackResult>? = null

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

    fun getRewardCashbacks(
        rewardCashbacksCallback: ApiResponseCallback<RewardCashbackResult>,
        accountId: String,
        startMonth: String?,
        endMonth: String?,
        amountStart: String?,
        amountEnd: String?,
        startDate: Int?,
        endDate: Int?,
        search: String?
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.rewardCashbacksCallback = rewardCashbacksCallback
        callApi(
            serviceApi.getRewardCashbacks(
                BettrApiSdk.getOrganizationId(),
                accountId,
                startMonth, endMonth, amountStart, amountEnd, startDate, endDate, search
            ), ApiTag.REWARD_CASHBACKS_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "reward points fetched")
                val rewardPointsApiResponse = response as RewardPointsApiResponse
                rewardPointsCallback?.onSuccess(rewardPointsApiResponse.results!!)
            }

            ApiTag.REWARD_CASHBACKS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "reward cashbacks fetched")
                val rewardCashbacksApiResponse = response as RewardCashbackApiResponse
                rewardCashbacksCallback?.onSuccess(rewardCashbacksApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(errorMessage)
            }

            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }

            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}