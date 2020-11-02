package com.mvalu.bettr_api.rewards

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackApiResponse
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackInfo
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackInfoApiResponse
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackResult
import com.mvalu.bettr_api.settings.SettingsGenericApiResponse
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object Rewards : ApiSdkBase() {
    private const val TAG = "Rewards"
    private var rewardPointsCallback: ApiResponseCallback<RewardPointsResult>? = null
    private var rewardCashbacksCallback: ApiResponseCallback<RewardCashbackResult>? = null
    private var rewardPointsSummaryCallback: ApiResponseCallback<RewardPointsSummaryResult>? = null
    private var rewardCashbackInfoCallback: ApiResponseCallback<RewardCashbackInfo>? = null
    private var redeemPointsCallback: ApiResponseCallback<Boolean>? = null

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
        startDate: String?,
        endDate: String?,
        search: String?,
        offset: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.rewardPointsCallback = rewardPointsCallback
        callApi(
            serviceApi.getRewardPoints(
                BettrApiSdk.getOrganizationId(),
                accountId,
                startMonth, endMonth, pointStart, pointEnd, startDate, endDate, search, offset
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
        startDate: String?,
        endDate: String?,
        search: String?,
        offset: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.rewardCashbacksCallback = rewardCashbacksCallback
        callApi(
            serviceApi.getRewardCashbacks(
                BettrApiSdk.getOrganizationId(),
                accountId,
                startMonth, endMonth, amountStart, amountEnd, startDate, endDate, search, offset
            ), ApiTag.REWARD_CASHBACKS_API
        )
    }

    fun getRewardPointsSummary(
        rewardPointsSummaryCallback: ApiResponseCallback<RewardPointsSummaryResult>,
        accountId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.rewardPointsSummaryCallback = rewardPointsSummaryCallback
        callApi(
            serviceApi.getRewardPointsSummary(
                BettrApiSdk.getOrganizationId(),
                accountId
            ), ApiTag.REWARD_POINTS_SUMMARY_API
        )
    }

    fun getRewardCashbackInfo(
        rewardCashbackInfoCallback: ApiResponseCallback<RewardCashbackInfo>,
        accountId: String,
        rewardCashbackId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.rewardCashbackInfoCallback = rewardCashbackInfoCallback
        callApi(
            serviceApi.getRewardCashbackInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                rewardCashbackId
            ), ApiTag.REWARD_CASHBACK_INFO_API
        )
    }

    fun redeemRewardPoints(
        redeemPointsCallback: ApiResponseCallback<Boolean>,
        accountId: String,
        points: Int
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.redeemPointsCallback = redeemPointsCallback
        callApi(
            serviceApi.redeemRewardPoints(
                BettrApiSdk.getOrganizationId(),
                accountId,
                RewardPointsRedeemRequest(points)
            ), ApiTag.REWARD_POINTS_REDEEM_API
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

            ApiTag.REWARD_POINTS_SUMMARY_API -> {
                BettrApiSdkLogger.printInfo(TAG, "reward points summary fetched")
                val rewardPointsSummaryApiResponse = response as RewardPointsSummaryApiResponse
                rewardPointsSummaryCallback?.onSuccess(rewardPointsSummaryApiResponse.results!!)
            }

            ApiTag.REWARD_CASHBACK_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "reward cashback info fetched")
                val rewardCashbackInfoApiResponse = response as RewardCashbackInfoApiResponse
                rewardCashbackInfoCallback?.onSuccess(rewardCashbackInfoApiResponse.results!!)
            }
            ApiTag.REWARD_POINTS_REDEEM_API -> {
                BettrApiSdkLogger.printInfo(TAG, "reward points redeemed")
                val settingsGenericApiResponse = response as SettingsGenericApiResponse
                redeemPointsCallback?.onSuccess(settingsGenericApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(errorCode, errorMessage)
            }

            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(errorCode, errorMessage)
            }

            ApiTag.REWARD_POINTS_SUMMARY_API -> {
                rewardPointsSummaryCallback?.onError(errorCode, errorMessage)
            }

            ApiTag.REWARD_CASHBACK_INFO_API -> {
                rewardCashbackInfoCallback?.onError(errorCode, errorMessage)
            }

            ApiTag.REWARD_POINTS_REDEEM_API -> {
                redeemPointsCallback?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.REWARD_POINTS_SUMMARY_API -> {
                rewardPointsSummaryCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.REWARD_CASHBACK_INFO_API -> {
                rewardCashbackInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.REWARD_POINTS_REDEEM_API -> {
                redeemPointsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }

            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }

            ApiTag.REWARD_POINTS_SUMMARY_API -> {
                rewardPointsSummaryCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.REWARD_CASHBACK_INFO_API -> {
                rewardCashbackInfoCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.REWARD_POINTS_REDEEM_API -> {
                redeemPointsCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.REWARD_POINTS_API -> {
                rewardPointsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.REWARD_CASHBACKS_API -> {
                rewardCashbacksCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.REWARD_POINTS_SUMMARY_API -> {
                rewardPointsSummaryCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.REWARD_CASHBACK_INFO_API -> {
                rewardCashbackInfoCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.REWARD_POINTS_REDEEM_API -> {
                redeemPointsCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }
}