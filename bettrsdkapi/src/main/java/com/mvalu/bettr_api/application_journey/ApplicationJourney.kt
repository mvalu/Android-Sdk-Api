package com.mvalu.bettr_api.application_journey

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.application_journey.bureau.BureauStatusApiResponse
import com.mvalu.bettr_api.application_journey.bureau.BureauStatusRequest
import com.mvalu.bettr_api.application_journey.bureau.BureauStatusResult
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberApiResponse
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberRequest
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberResult
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeApiResponse
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeResult
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object ApplicationJourney : ApiSdkBase() {
    private const val TAG = "ApplicationJourney"

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    private var updateLeadCallBack: ApiResponseCallback<LeadDetail>? = null
    private var validatePANNumberCallBack: ApiResponseCallback<ValidatePANNumberResult>? = null
    private var validatePincodeCallBack: ApiResponseCallback<ValidatePincodeResult>? = null
    private var bureauStatusCallBack: ApiResponseCallback<BureauStatusResult>? = null

    fun updateLead(
        leadId: String,
        leadDetail: LeadDetail,
        updateLeadCallBack: ApiResponseCallback<LeadDetail>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.updateLeadCallBack = updateLeadCallBack
        callApi(
            serviceApi.updateLead(BettrApiSdk.getOrganizationId(), leadId, leadDetail),
            ApiTag.UPDATE_LEAD_API
        )
    }

    fun validatePANNumber(
        panNumber: String,
        leadName: String,
        validatePANNumberCallBack: ApiResponseCallback<ValidatePANNumberResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.validatePANNumberCallBack = validatePANNumberCallBack
        val validatePANNumberRequest = ValidatePANNumberRequest()
            .apply {
                pan = panNumber
                name = leadName

            }
        callApi(
            serviceApi.validatePANNumber(BettrApiSdk.getOrganizationId(), validatePANNumberRequest),
            ApiTag.VALIDATE_PAN_API
        )
    }

    fun validatePincode(
        pincode: String,
        validatePincodeCallBack: ApiResponseCallback<ValidatePincodeResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.validatePincodeCallBack = validatePincodeCallBack
        callApi(
            serviceApi.validatePincode(BettrApiSdk.getOrganizationId(), pincode),
            ApiTag.VALIDATE_PINCODE_API
        )
    }

    fun checkBureauStatus(
        userId: String,
        leadId: String,
        bureauStatusCallBack: ApiResponseCallback<BureauStatusResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.bureauStatusCallBack = bureauStatusCallBack
        val bureauStatusRequest = BureauStatusRequest().apply {
            this.userId = userId
            this.leadId = leadId
        }
        callApi(
            serviceApi.checkBureauStatus(BettrApiSdk.getOrganizationId(), bureauStatusRequest),
            ApiTag.BUREAU_STATUS_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.UPDATE_LEAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Lead updated successfully")
                val updateLeadApiResponse = response as LeadDetailApiResponse
                updateLeadCallBack?.onSuccess(updateLeadApiResponse.results!!)
            }
            ApiTag.VALIDATE_PAN_API -> {
                BettrApiSdkLogger.printInfo(TAG, "validate pan called successfully")
                val validatePANApiResponse = response as ValidatePANNumberApiResponse
                validatePANNumberCallBack?.onSuccess(validatePANApiResponse.results!!)
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                BettrApiSdkLogger.printInfo(TAG, "validate pincode called successfully")
                val validatePincodeApiResponse = response as ValidatePincodeApiResponse
                validatePincodeCallBack?.onSuccess(validatePincodeApiResponse.results!!)
            }
            ApiTag.BUREAU_STATUS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "bureau status fetched successfully")
                val bureauStatusApiResponse = response as BureauStatusApiResponse
                bureauStatusCallBack?.onSuccess(bureauStatusApiResponse.results!!)
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(errorMessage)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(errorMessage)
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(errorMessage)
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }
}