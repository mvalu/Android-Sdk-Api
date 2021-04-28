package com.mvalu.bettr_api.emi

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.network.ApiTag

object Emi : ApiSdkBase() {
    private const val TAG = "Emi"

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }


    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        TODO("Not yet implemented")
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        TODO("Not yet implemented")
    }

    override fun onTimeout(apiTag: ApiTag) {
        TODO("Not yet implemented")
    }

    override fun onNetworkError(apiTag: ApiTag) {
        TODO("Not yet implemented")
    }

    override fun onAuthError(apiTag: ApiTag) {
        TODO("Not yet implemented")
    }
}