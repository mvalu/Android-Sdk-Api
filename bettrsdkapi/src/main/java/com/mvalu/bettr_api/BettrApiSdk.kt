package com.mvalu.bettr_api

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.injection.component.AppComponent
import com.mvalu.bettr_api.injection.component.DaggerAppComponent
import com.mvalu.bettr_api.internal.CryptLib
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.internal.Validate
import com.mvalu.bettr_api.login.GenerateTokenRequest
import com.mvalu.bettr_api.login.GenerateTokenResponse
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.network.CampaignInfo
import com.mvalu.bettr_api.network.DeviceInfo
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object BettrApiSdk : ApiSdkBase() {

    private var daggerComponent: AppComponent? = null

    init {
        daggerComponent = DaggerAppComponent.builder().build()
    }

    private const val TAG = "BettrApiSdk"
    private var applicationContext: Context? = null
    private var campaignInfo: CampaignInfo? = null
    private var ACCESS_KEY: String = ""
    private var SECRET_KEY: String = ""
    private var ORGANIZATION_ID: String = ""
    private var USER_ID: String = ""
    private var isSdkInitialized = false
    private var isLoggingBehaviourEnabled: Boolean = false
    private var ACCESS_TOKEN: String = ""
    private var initCallback: BettrApiSdkCallback? = null

    internal fun getAppComponent(): AppComponent {
        return daggerComponent!!
    }

    fun isSdkInitialized(): Boolean {
        return isSdkInitialized && !ACCESS_TOKEN.isNullOrEmpty()
    }

    fun isLoggingBehaviourEnabled(): Boolean {
        return isLoggingBehaviourEnabled
    }

    fun getAccessToken(): String {
        return ACCESS_TOKEN
    }

    fun getAccessKey(): String {
        return ACCESS_KEY
    }

    fun getSecretKey(): String {
        return SECRET_KEY
    }

    fun getOrganizationId(): String {
        return ORGANIZATION_ID
    }

    fun getUserId(): String {
        return USER_ID
    }

    fun getApplicationContext(): Context {
        return applicationContext!!
    }

    /**
     * Initialize sdk here with required parameters
     * if required parameters are null or empty throw error
     */
    fun initializeSDK(
        applicationContext: Context,
        ACCESS_KEY: String,
        SECRET_KEY: String,
        ORGANIZATION_ID: String,
        USER_ID: String,
        campaignInfo: CampaignInfo?,
        initCallback: BettrApiSdkCallback?
    ) {
        daggerComponent?.inject(this)
        this.initCallback = initCallback

        if (isSdkInitialized) {
            initCallback?.onError("Sdk already initialized")
            BettrApiSdkLogger.printError(TAG, "Sdk already initialized")
        } else {
            Validate.notNull(applicationContext, "Application context")
            Validate.hasInternetPermissions(applicationContext, true)
//            Validate.hasReadPhoneStatePermissions(applicationContext, true)
            Validate.notNullorEmpty(ACCESS_KEY, "ACCESS_KEY")
            Validate.notNullorEmpty(SECRET_KEY, "SECRET_KEY")
            Validate.notNullorEmpty(ORGANIZATION_ID, "ORGANIZATION_ID")
            Validate.notNullorEmpty(USER_ID, "USER_ID")

            this.campaignInfo = campaignInfo
            this.applicationContext = applicationContext.applicationContext
            this.ACCESS_KEY = ACCESS_KEY
            this.SECRET_KEY = SECRET_KEY
            this.ORGANIZATION_ID = ORGANIZATION_ID
            this.USER_ID = USER_ID
            BettrApiSdkLogger.printInfo(
                TAG,
                "Sdk initialization initiated with " +
                        "ACCESS_KEY: $ACCESS_KEY SECRET_KEY: $SECRET_KEY " +
                        "ORGANIZATION_ID: $ORGANIZATION_ID USER_ID: $USER_ID"
            )

            val request = GenerateTokenRequest()
            request.identifier = USER_ID
            request.deviceInfo = getDeviceInfo()
            request.marketCampaign = campaignInfo
            request.imei = "4384834"//getImei(applicationContext)
            callApi(
                serviceApi.generateToken(getOrganizationId(), request),
                ApiTag.GENERATE_ACCESS_TOKEN_API
            )
        }
    }

    fun generateSignedSecretKey(): String {
        val key =
            ORGANIZATION_ID + "##" + ACCESS_KEY + "##" + SECRET_KEY + "##" + System.currentTimeMillis()
        return CryptLib().encryptPlainTextWithRandomIV(key, SECRET_KEY)
    }

    /**
     * enable logging behaviour to see logs in logcat
     */
    fun enableLoggingBehaviour(enableLoggingBehaviour: Boolean) {
        this.isLoggingBehaviourEnabled = enableLoggingBehaviour
    }

    private fun getDeviceInfo(): DeviceInfo {
        val deviceInfo = DeviceInfo()
        deviceInfo.id = Build.ID
        deviceInfo.brand = Build.BRAND
        deviceInfo.model = Build.MODEL
        deviceInfo.board = Build.BOARD
        deviceInfo.device = Build.DEVICE
        deviceInfo.display = Build.DISPLAY
        deviceInfo.hardware = Build.HARDWARE
        deviceInfo.fingerPrint = Build.FINGERPRINT
        deviceInfo.user = Build.USER
        deviceInfo.product = Build.PRODUCT
        deviceInfo.osApiLevel = Build.VERSION.SDK_INT
        deviceInfo.osVersion = Build.VERSION.RELEASE
        return deviceInfo
    }

    @SuppressLint("MissingPermission")
    private fun getImei(context: Context): String? {
        return try {
            val telService: TelephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                telService.imei
            } else {
                telService.deviceId
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.GENERATE_ACCESS_TOKEN_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Token generated successfully")
                val tokenResponse = response as GenerateTokenResponse
                ACCESS_TOKEN = tokenResponse.result?.token!!
                isSdkInitialized = true
                initCallback?.onSuccess()
            }
        }
    }

    override fun onApiError(apiTag: ApiTag, errorMessage: String) {
        when (apiTag) {
            ApiTag.GENERATE_ACCESS_TOKEN_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
                initCallback?.onError(errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GENERATE_ACCESS_TOKEN_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value
                )
                initCallback?.onError(ErrorMessage.SDK_INITIALIZATION_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GENERATE_ACCESS_TOKEN_API -> {
                BettrApiSdkLogger.printInfo(
                    TAG,
                    apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value
                )
                initCallback?.onError(ErrorMessage.SDK_INITIALIZATION_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GENERATE_ACCESS_TOKEN_API -> {
                BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
                initCallback?.onError(ErrorMessage.SDK_INITIALIZATION_ERROR.value)
            }
        }
    }
}