package com.mvalu.bettr_api.application_journey

import android.net.Uri
import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.application_journey.bureau.*
import com.mvalu.bettr_api.application_journey.documents.DocumentUploadApiResponse
import com.mvalu.bettr_api.application_journey.documents.DocumentUploadResult
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberApiResponse
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberRequest
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberResult
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeApiResponse
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeResult
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.network.DocumentUploadApiResponseCallback
import com.mvalu.bettr_api.network.ProgressRequestBody
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object ApplicationJourney : ApiSdkBase(), ProgressRequestBody.DocumentUploadCallbacks {
    private const val TAG = "ApplicationJourney"

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    private var updateLeadCallBack: ApiResponseCallback<LeadDetail>? = null
    private var validatePANNumberCallBack: ApiResponseCallback<ValidatePANNumberResult>? = null
    private var validatePincodeCallBack: ApiResponseCallback<ValidatePincodeResult>? = null
    private var bureauStatusCallBack: ApiResponseCallback<BureauStatusResult>? = null
    private var bureauQuestionCallBack: ApiResponseCallback<BureauQuestionResult>? = null
    private var bureauAnswerCallBack: ApiResponseCallback<BureauStatusResult>? = null
    private var uploadIdProofCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadAddressProofCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadBankStatementCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadSalarySlipCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadProfilePicCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadPanCardCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadAadharFrontCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadAadharBackCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null

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

    fun getBureauQuestion(
        userId: String,
        leadId: String,
        bureauApplicationId: String,
        bureauQuestionCallBack: ApiResponseCallback<BureauQuestionResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.bureauQuestionCallBack = bureauQuestionCallBack
        val bureauQuestionRequest = BureauQuestionRequest().apply {
            this.userId = userId
            this.applicationId = bureauApplicationId
            this.leadId = leadId
        }
        callApi(
            serviceApi.getBureauQuestion(BettrApiSdk.getOrganizationId(), bureauQuestionRequest),
            ApiTag.BUREAU_QUESTION_API
        )
    }

    fun bureauVerifyAnswer(
        userId: String,
        bureauApplicationId: String,
        bureauChallengeConfigGUID: String,
        bureauAnswers: List<BureauAnswerRequest.Answer>,
        bureauAnswerCallBack: ApiResponseCallback<BureauStatusResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.bureauAnswerCallBack = bureauAnswerCallBack
        val bureauAnswerRequest = BureauAnswerRequest().apply {
            this.userId = userId
            this.applicationId = bureauApplicationId
            this.answer = bureauAnswers
            this.challengeConfigGUID = bureauChallengeConfigGUID
        }
        callApi(
            serviceApi.bureauVerifyAnswer(BettrApiSdk.getOrganizationId(), bureauAnswerRequest),
            ApiTag.BUREAU_ANSWER_API
        )
    }

    fun uploadIdProof(
        fileUri: Uri, file: File,
        uploadIdProofCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadIdProofCallBack = uploadIdProofCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.ID_PROOF_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadIdProof(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.ID_PROOF_UPLOAD_API
        )
    }

    fun uploadAddressProof(
        fileUri: Uri, file: File,
        uploadAddressProofCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadAddressProofCallBack = uploadAddressProofCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.ADDRESS_PROOF_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadAddressProof(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.ADDRESS_PROOF_UPLOAD_API
        )
    }

    fun uploadBankStatement(
        fileUri: Uri, file: File,
        uploadBankStatementCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadBankStatementCallBack = uploadBankStatementCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.BANK_STATEMENT_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadBankStatement(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.BANK_STATEMENT_UPLOAD_API
        )
    }

    fun uploadSalarySlip(
        fileUri: Uri, file: File,
        uploadSalarySlipCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadSalarySlipCallBack = uploadSalarySlipCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.SALARY_SLIP_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadSalarySlip(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.SALARY_SLIP_UPLOAD_API
        )
    }

    fun uploadProfilePic(
        fileUri: Uri, file: File,
        uploadProfilePicCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadProfilePicCallBack = uploadProfilePicCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.PROFILE_PIC_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadProfilePic(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.PROFILE_PIC_UPLOAD_API
        )
    }

    fun uploadPanCard(
        fileUri: Uri, file: File,
        uploadPanCardCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadPanCardCallBack = uploadPanCardCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.PAN_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadPanCard(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.PAN_UPLOAD_API
        )
    }

    fun uploadAadharFront(
        fileUri: Uri, file: File,
        uploadAadharFrontCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadAadharFrontCallBack = uploadAadharFrontCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.AADHAR_FRONT_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadAadharFront(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.AADHAR_FRONT_UPLOAD_API
        )
    }

    fun uploadAadharBack(
        fileUri: Uri, file: File,
        uploadAadharBackCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadAadharBackCallBack = uploadAadharBackCallBack

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            BettrApiSdk.getApplicationContext().contentResolver?.getType(fileUri)!!,
            file,
            ApiTag.AADHAR_BACK_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadAadharBack(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.AADHAR_BACK_UPLOAD_API
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
            ApiTag.BUREAU_QUESTION_API -> {
                BettrApiSdkLogger.printInfo(TAG, "bureau question fetched successfully")
                val bureauQuestionApiResponse = response as BureauQuestionApiResponse
                bureauQuestionCallBack?.onSuccess(bureauQuestionApiResponse.results!!)
            }
            ApiTag.BUREAU_ANSWER_API -> {
                BettrApiSdkLogger.printInfo(TAG, "bureau answer verified successfully")
                val bureauAnswerApiResponse = response as BureauStatusApiResponse
                bureauAnswerCallBack?.onSuccess(bureauAnswerApiResponse.results!!)
            }
            ApiTag.ID_PROOF_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "id proof uploaded successfully")
                val idProofUploadApiResponse = response as DocumentUploadApiResponse
                uploadIdProofCallBack?.onSuccess(idProofUploadApiResponse.results!!)
            }
            ApiTag.ADDRESS_PROOF_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "address proof uploaded successfully")
                val addressProofUploadApiResponse = response as DocumentUploadApiResponse
                uploadAddressProofCallBack?.onSuccess(addressProofUploadApiResponse.results!!)
            }
            ApiTag.BANK_STATEMENT_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "bank statement uploaded successfully")
                val bankStatementUploadApiResponse = response as DocumentUploadApiResponse
                uploadBankStatementCallBack?.onSuccess(bankStatementUploadApiResponse.results!!)
            }
            ApiTag.SALARY_SLIP_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "salary slip uploaded successfully")
                val salarySlipUploadApiResponse = response as DocumentUploadApiResponse
                uploadSalarySlipCallBack?.onSuccess(salarySlipUploadApiResponse.results!!)
            }
            ApiTag.PROFILE_PIC_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "profile pic uploaded successfully")
                val profilePicUploadApiResponse = response as DocumentUploadApiResponse
                uploadProfilePicCallBack?.onSuccess(profilePicUploadApiResponse.results!!)
            }
            ApiTag.PAN_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "pan card uploaded successfully")
                val panCardUploadApiResponse = response as DocumentUploadApiResponse
                uploadPanCardCallBack?.onSuccess(panCardUploadApiResponse.results!!)
            }
            ApiTag.AADHAR_FRONT_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "aadhar front uploaded successfully")
                val aadharFrontUploadApiResponse = response as DocumentUploadApiResponse
                uploadAadharFrontCallBack?.onSuccess(aadharFrontUploadApiResponse.results!!)
            }
            ApiTag.AADHAR_BACK_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "aadhar back uploaded successfully")
                val aadharBackUploadApiResponse = response as DocumentUploadApiResponse
                uploadAadharBackCallBack?.onSuccess(aadharBackUploadApiResponse.results!!)
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
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(errorMessage)
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(errorMessage)
            }
            ApiTag.ID_PROOF_UPLOAD_API -> {
                uploadIdProofCallBack?.onError(errorMessage)
            }
            ApiTag.ADDRESS_PROOF_UPLOAD_API -> {
                uploadAddressProofCallBack?.onError(errorMessage)
            }
            ApiTag.BANK_STATEMENT_UPLOAD_API -> {
                uploadBankStatementCallBack?.onError(errorMessage)
            }
            ApiTag.SALARY_SLIP_UPLOAD_API -> {
                uploadSalarySlipCallBack?.onError(errorMessage)
            }
            ApiTag.PROFILE_PIC_UPLOAD_API -> {
                uploadProfilePicCallBack?.onError(errorMessage)
            }
            ApiTag.PAN_UPLOAD_API -> {
                uploadPanCardCallBack?.onError(errorMessage)
            }
            ApiTag.AADHAR_FRONT_UPLOAD_API -> {
                uploadAadharFrontCallBack?.onError(errorMessage)
            }
            ApiTag.AADHAR_BACK_UPLOAD_API -> {
                uploadAadharBackCallBack?.onError(errorMessage)
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
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.ID_PROOF_UPLOAD_API -> {
                uploadIdProofCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.ADDRESS_PROOF_UPLOAD_API -> {
                uploadAddressProofCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.BANK_STATEMENT_UPLOAD_API -> {
                uploadBankStatementCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.SALARY_SLIP_UPLOAD_API -> {
                uploadSalarySlipCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.PROFILE_PIC_UPLOAD_API -> {
                uploadProfilePicCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.PAN_UPLOAD_API -> {
                uploadPanCardCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.AADHAR_FRONT_UPLOAD_API -> {
                uploadAadharFrontCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.AADHAR_BACK_UPLOAD_API -> {
                uploadAadharBackCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
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
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.ID_PROOF_UPLOAD_API -> {
                uploadIdProofCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.ADDRESS_PROOF_UPLOAD_API -> {
                uploadAddressProofCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.BANK_STATEMENT_UPLOAD_API -> {
                uploadBankStatementCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.SALARY_SLIP_UPLOAD_API -> {
                uploadSalarySlipCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.PROFILE_PIC_UPLOAD_API -> {
                uploadProfilePicCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.PAN_UPLOAD_API -> {
                uploadPanCardCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.AADHAR_FRONT_UPLOAD_API -> {
                uploadAadharFrontCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.AADHAR_BACK_UPLOAD_API -> {
                uploadAadharBackCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
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
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.ID_PROOF_UPLOAD_API -> {
                uploadIdProofCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.ADDRESS_PROOF_UPLOAD_API -> {
                uploadAddressProofCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.BANK_STATEMENT_UPLOAD_API -> {
                uploadBankStatementCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.SALARY_SLIP_UPLOAD_API -> {
                uploadSalarySlipCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.PROFILE_PIC_UPLOAD_API -> {
                uploadProfilePicCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.PAN_UPLOAD_API -> {
                uploadPanCardCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.AADHAR_FRONT_UPLOAD_API -> {
                uploadAadharFrontCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.AADHAR_BACK_UPLOAD_API -> {
                uploadAadharBackCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
        }
    }

    override fun onProgressUpdate(percentage: Int, apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.ID_PROOF_UPLOAD_API -> {
                uploadIdProofCallBack?.progressUpdate(percentage)
            }
            ApiTag.ADDRESS_PROOF_UPLOAD_API -> {
                uploadAddressProofCallBack?.progressUpdate(percentage)
            }
            ApiTag.BANK_STATEMENT_UPLOAD_API -> {
                uploadBankStatementCallBack?.progressUpdate(percentage)
            }
            ApiTag.SALARY_SLIP_UPLOAD_API -> {
                uploadSalarySlipCallBack?.progressUpdate(percentage)
            }
            ApiTag.PROFILE_PIC_UPLOAD_API -> {
                uploadProfilePicCallBack?.progressUpdate(percentage)
            }
            ApiTag.PAN_UPLOAD_API -> {
                uploadPanCardCallBack?.progressUpdate(percentage)
            }
            ApiTag.AADHAR_FRONT_UPLOAD_API -> {
                uploadAadharFrontCallBack?.progressUpdate(percentage)
            }
            ApiTag.AADHAR_BACK_UPLOAD_API -> {
                uploadAadharBackCallBack?.progressUpdate(percentage)
            }
        }
    }
}