package com.mvalu.bettr_api.settings

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.home_module.CardInfo
import com.mvalu.bettr_api.internal.CryptLib
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.settings.plastic_card.CardActivationRequest
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE

object Settings : ApiSdkBase() {

    private const val TAG = "Settings"
    private var settingsResponseCallback: ApiResponseCallback<SettingsInfo>? = null
    private var cardImageResponseCallBack: ApiResponseCallback<String>? = null
    private var settingsGenericResponseCallBack: ApiResponseCallback<Boolean>? = null
    private var initPinResponseCallBack: ApiResponseCallback<PinInitResult>? = null
    private var blockCardResponseCallBack: ApiResponseCallback<CardInfo>? = null

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    fun getSettingsInfo(
        settingsResponseCallback: ApiResponseCallback<SettingsInfo>,
        accountId: String,
        cardId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.settingsResponseCallback = settingsResponseCallback
        callApi(
            serviceApi.getSettingsInfo(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId
            ),
            ApiTag.SETTINGS_INFO_API
        )
    }

    fun getCardImage(
        cardImageResponseCallBack: ApiResponseCallback<String>,
        accountId: String,
        cardId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.cardImageResponseCallBack = cardImageResponseCallBack
        callApi(
            serviceApi.getCardImage(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId
            ),
            ApiTag.CARD_IMAGE_API
        )
    }

    fun initializeCardPin(
        initPinResponseCallBack: ApiResponseCallback<PinInitResult>,
        accountId: String,
        cardId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.initPinResponseCallBack = initPinResponseCallBack
        callApi(
            serviceApi.initializeCardPin(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId
            ),
            ApiTag.PIN_INIT_API
        )
    }

    fun setCardPin(
        settingsGenericResponseCallBack: ApiResponseCallback<Boolean>,
        accountId: String,
        cardId: String,
        pin: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.settingsGenericResponseCallBack = settingsGenericResponseCallBack
        callApi(
            serviceApi.setCardPin(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId,
                PinSetRequest(getEncryptedPin(pin))
            ),
            ApiTag.PIN_SET_API
        )
    }

    fun blockCard(
        blockCardResponseCallBack: ApiResponseCallback<CardInfo>,
        accountId: String,
        cardId: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.blockCardResponseCallBack = blockCardResponseCallBack
        callApi(
            serviceApi.blockCard(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId
            ),
            ApiTag.CARD_BLOCK_API
        )
    }

    fun activateDigitalCard(
        settingsGenericResponseCallBack: ApiResponseCallback<Boolean>,
        accountId: String,
        pin: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.settingsGenericResponseCallBack = settingsGenericResponseCallBack
        callApi(
            serviceApi.activateDigitalCard(
                BettrApiSdk.getOrganizationId(),
                accountId,
                CardActivationRequest(getEncryptedPin(pin))
            ),
            ApiTag.ACTIVATE_DIGITAL_CARD_API
        )
    }

    fun activatePlasticCard(
        settingsGenericResponseCallBack: ApiResponseCallback<Boolean>,
        accountId: String,
        pin: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.settingsGenericResponseCallBack = settingsGenericResponseCallBack
        callApi(
            serviceApi.activatePlasticCard(
                BettrApiSdk.getOrganizationId(),
                accountId,
                CardActivationRequest(getEncryptedPin(pin))
            ),
            ApiTag.ACTIVATE_PLASTIC_CARD_API
        )
    }

    fun cardOnOff(
        blockCardResponseCallBack: ApiResponseCallback<CardInfo>,
        accountId: String,
        cardId: String,
        action: String
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.blockCardResponseCallBack = blockCardResponseCallBack
        callApi(
            serviceApi.cardOnOff(
                BettrApiSdk.getOrganizationId(),
                accountId,
                cardId,
                CardOnOffRequest(action)
            ),
            ApiTag.CARD_ON_OFF_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Settings info fetched")
                val settingsInfoApiResponse = response as SettingsInfoApiResponse
                settingsResponseCallback?.onSuccess(settingsInfoApiResponse.results!!)
            }
            ApiTag.CARD_IMAGE_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Card image fetched")
                val cardImageApiResponse = response as CardImageApiResponse
                cardImageResponseCallBack?.onSuccess(cardImageApiResponse.results!!)
            }
            ApiTag.PIN_INIT_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Initialized pin")
                val pinInitApiResponse = response as PinInitApiResponse
                initPinResponseCallBack?.onSuccess(pinInitApiResponse.results!!)
            }
            ApiTag.PIN_SET_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Set pin")
                val settingsGenericApiResponse = response as SettingsGenericApiResponse
                settingsGenericResponseCallBack?.onSuccess(settingsGenericApiResponse.results!!)
            }
            ApiTag.CARD_BLOCK_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Blocked card")
                val blockCardApiResponse = response as BlockCardApiResponse
                blockCardResponseCallBack?.onSuccess(blockCardApiResponse.results!!)
            }
            ApiTag.ACTIVATE_DIGITAL_CARD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Activated digital card")
                val settingsGenericApiResponse = response as SettingsGenericApiResponse
                settingsGenericResponseCallBack?.onSuccess(settingsGenericApiResponse.results!!)
            }
            ApiTag.ACTIVATE_PLASTIC_CARD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Activated plastic card")
                val settingsGenericApiResponse = response as SettingsGenericApiResponse
                settingsGenericResponseCallBack?.onSuccess(settingsGenericApiResponse.results!!)
            }
            ApiTag.CARD_ON_OFF_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Card on off")
                val blockCardApiResponse = response as BlockCardApiResponse
                blockCardResponseCallBack?.onSuccess(blockCardApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(errorCode, errorMessage)
            }
            ApiTag.CARD_IMAGE_API -> {
                cardImageResponseCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.PIN_INIT_API -> {
                initPinResponseCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.CARD_BLOCK_API, ApiTag.CARD_ON_OFF_API -> {
                blockCardResponseCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.PIN_SET_API,
            ApiTag.ACTIVATE_DIGITAL_CARD_API,
            ApiTag.ACTIVATE_PLASTIC_CARD_API -> {
                settingsGenericResponseCallBack?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
        )
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.CARD_IMAGE_API -> {
                cardImageResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.PIN_INIT_API -> {
                initPinResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.CARD_BLOCK_API, ApiTag.CARD_ON_OFF_API -> {
                blockCardResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.PIN_SET_API,
            ApiTag.ACTIVATE_DIGITAL_CARD_API,
            ApiTag.ACTIVATE_PLASTIC_CARD_API -> {
                settingsGenericResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(
            TAG,
            apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
        )
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.CARD_IMAGE_API -> {
                cardImageResponseCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.PIN_INIT_API -> {
                initPinResponseCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.CARD_BLOCK_API, ApiTag.CARD_ON_OFF_API -> {
                blockCardResponseCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.PIN_SET_API,
            ApiTag.ACTIVATE_DIGITAL_CARD_API,
            ApiTag.ACTIVATE_PLASTIC_CARD_API -> {
                settingsGenericResponseCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.SETTINGS_INFO_API -> {
                settingsResponseCallback?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.CARD_IMAGE_API -> {
                cardImageResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.PIN_INIT_API -> {
                initPinResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.CARD_BLOCK_API, ApiTag.CARD_ON_OFF_API -> {
                blockCardResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.PIN_SET_API,
            ApiTag.ACTIVATE_DIGITAL_CARD_API,
            ApiTag.ACTIVATE_PLASTIC_CARD_API -> {
                settingsGenericResponseCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
        }
    }

    private fun getEncryptedPin(pin: String): String {
        return CryptLib().encryptPlainTextWithRandomIV(
            pin,
            CryptLib.CRYPT_KEY
        )
    }
}