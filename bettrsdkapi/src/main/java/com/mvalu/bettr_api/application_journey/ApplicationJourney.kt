package com.mvalu.bettr_api.application_journey

import android.net.Uri
import com.mvalu.bettr_api.BankAccountIFSCResponseModel
import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.PRODUCT_TYPE
import com.mvalu.bettr_api.application_journey.bureau.*
import com.mvalu.bettr_api.application_journey.checklist.CheckListApiResponse
import com.mvalu.bettr_api.application_journey.checklist.CheckListRequest
import com.mvalu.bettr_api.application_journey.checklist.CheckListResult
import com.mvalu.bettr_api.application_journey.content.ApplicationJourneyContentApiResponse
import com.mvalu.bettr_api.application_journey.content.ApplicationJourneyContentRequest
import com.mvalu.bettr_api.application_journey.content.ApplicationJourneyContentResult
import com.mvalu.bettr_api.application_journey.documents.*
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberApiResponse
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberRequest
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberResult
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeApiResponse
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeResult
import com.mvalu.bettr_api.base.ApiSdkBase
import com.mvalu.bettr_api.internal.CryptLib
import com.mvalu.bettr_api.internal.ErrorMessage
import com.mvalu.bettr_api.network.ApiResponseCallback
import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.network.DocumentUploadApiResponseCallback
import com.mvalu.bettr_api.network.ProgressRequestBody
import com.mvalu.bettr_api.utils.ApiSdkFileUtils
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import com.mvalu.bettr_api.utils.NO_NETWORK_ERROR_CODE
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

object ApplicationJourney : ApiSdkBase(), ProgressRequestBody.DocumentUploadCallbacks {
    private const val TAG = "ApplicationJourney"

    init {
        BettrApiSdk.getAppComponent().inject(this)
    }

    private var updateLeadCallBack: ApiResponseCallback<LeadDetail>? = null
    private var getLeadCallBack: ApiResponseCallback<LeadDetail>? = null
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
    private var uploadCompanyIDCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadCompanyBusinessCardCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadGSTInvoiceCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadGSTIssueCertificateCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadShopInsideCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadShopOutsideCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var uploadShopRegistrationCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>? =
        null
    private var verifyDocumentsCallBack: ApiResponseCallback<VerifyDocumentsResult>? =
        null
    private var checkListCallBack: ApiResponseCallback<CheckListResult>? =
        null
    private var applicationJourneyContentCallBack: ApiResponseCallback<ApplicationJourneyContentResult>? =
        null
    private var branchDetailsCallBack: ApiResponseCallback<List<BranchDetail>>? =
        null
    private var ifscDetailsCallBack: ApiResponseCallback<List<BankAccountIFSCResponseModel.IFSC>>? =
        null

    fun getLead(
        leadId: String,
        getLeadCallBack: ApiResponseCallback<LeadDetail>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.getLeadCallBack = getLeadCallBack
        callApi(
            serviceApi.getLead(BettrApiSdk.getOrganizationId(), leadId),
            ApiTag.GET_LEAD_API
        )
    }

    fun updateLead(
        leadId: String,
        leadDetail: LeadDetail,
        updateLeadCallBack: ApiResponseCallback<LeadDetail>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.updateLeadCallBack = updateLeadCallBack
        leadDetail.productType = PRODUCT_TYPE
        encryptLeadData(leadDetail)
        callApi(
            serviceApi.updateLead(BettrApiSdk.getOrganizationId(), leadId, leadDetail),
            ApiTag.UPDATE_LEAD_API
        )
    }

    private fun encryptLeadData(leadDetail: LeadDetail) {
        if (!leadDetail.userDetail?.panNumber.isNullOrEmpty()) {
            leadDetail.userDetail?.panNumber =
                getEncryptedData(leadDetail.userDetail?.panNumber!!)
        }
        if (!leadDetail.userDetail?.bankAccountNumber.isNullOrEmpty()) {
            leadDetail.userDetail?.bankAccountNumber =
                getEncryptedData(leadDetail.userDetail?.bankAccountNumber!!)
        }
        if (!leadDetail.userDetail?.bankIfsc.isNullOrEmpty()) {
            leadDetail.userDetail?.bankIfsc =
                getEncryptedData(leadDetail.userDetail?.bankIfsc!!)
        }
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
        leadId: String,
        bureauStatusCallBack: ApiResponseCallback<BureauStatusResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.bureauStatusCallBack = bureauStatusCallBack
        val bureauStatusRequest = BureauStatusRequest().apply {
            this.userId = BettrApiSdk.getUserId()
            this.leadId = leadId
        }
        callApi(
            serviceApi.checkBureauStatus(BettrApiSdk.getOrganizationId(), bureauStatusRequest),
            ApiTag.BUREAU_STATUS_API
        )
    }

    fun getBureauQuestion(
        leadId: String,
        bureauApplicationId: String,
        bureauQuestionCallBack: ApiResponseCallback<BureauQuestionResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.bureauQuestionCallBack = bureauQuestionCallBack
        val bureauQuestionRequest = BureauQuestionRequest().apply {
            this.userId = BettrApiSdk.getUserId()
            this.applicationId = bureauApplicationId
            this.leadId = leadId
        }
        callApi(
            serviceApi.getBureauQuestion(BettrApiSdk.getOrganizationId(), bureauQuestionRequest),
            ApiTag.BUREAU_QUESTION_API
        )
    }

    fun bureauVerifyAnswer(
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
            this.userId = BettrApiSdk.getUserId()
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadAddressProofCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadBankStatementCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadSalarySlipCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadProfilePicCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadPanCardCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadAadharFrontCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadAadharBackCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
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

    fun verifyDocuments(
        panCard: String?,
        profilePic: String?,
        aadharFront: String?,
        aadharBack: String?,
        applicationId: String,
        verifyDocumentsCallBack: ApiResponseCallback<VerifyDocumentsResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.verifyDocumentsCallBack = verifyDocumentsCallBack
        val verifyDocumentsRequest = VerifyDocumentsRequest().apply {
            this.userId = BettrApiSdk.getUserId()
            this.leadId = applicationId
            this.pan = panCard
            this.photo = profilePic
            this.aadharFront = aadharFront
            this.aadharBack = aadharBack
            this.productType = PRODUCT_TYPE
        }
        callApi(
            serviceApi.verifyDocuments(
                BettrApiSdk.getOrganizationId(),
                applicationId,
                verifyDocumentsRequest
            ),
            ApiTag.VERIFY_DOCUMENTS_API
        )
    }

    fun fetchCheckList(
        leadId: String,
        checkListCallBack: ApiResponseCallback<CheckListResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.checkListCallBack = checkListCallBack

        val checkListRequest = CheckListRequest().apply {
            this.productType = PRODUCT_TYPE
            this.leadId = leadId
        }
        callApi(
            serviceApi.fetchCheckList(BettrApiSdk.getOrganizationId(), checkListRequest),
            ApiTag.CHECKLIST_API
        )
    }

    fun fetchApplicationJourneyContent(
        applicationJourneyContentCallBack: ApiResponseCallback<ApplicationJourneyContentResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.applicationJourneyContentCallBack = applicationJourneyContentCallBack

        val request = ApplicationJourneyContentRequest().apply {

        }
        callApi(
            serviceApi.fetchApplicationJourneyContent(BettrApiSdk.getOrganizationId(), request),
            ApiTag.APPLICATION_JOURNEY_CONTENT_API
        )
    }

    private fun getEncryptedData(value: String): String {
        return CryptLib().encryptPlainTextWithRandomIV(
            value,
            CryptLib.CRYPT_KEY
        )
    }

    private fun getDecryptedData(value: String): String {
        return CryptLib().decryptCipherTextWithRandomIV(
            value,
            CryptLib.CRYPT_KEY
        )
    }

    private fun decryptLeadData(leadDetail: LeadDetail) {
        if (!leadDetail.userDetail?.panNumber.isNullOrEmpty()) {
            leadDetail.userDetail?.panNumber =
                getDecryptedData(leadDetail.userDetail?.panNumber!!)
        }

        if (!leadDetail.userDetail?.bankAccountNumber.isNullOrEmpty()) {
            leadDetail.userDetail?.bankAccountNumber =
                getDecryptedData(leadDetail.userDetail?.bankAccountNumber!!)
        }

        if (!leadDetail.userDetail?.bankIfsc.isNullOrEmpty()) {
            leadDetail.userDetail?.bankIfsc =
                getDecryptedData(leadDetail.userDetail?.bankIfsc!!)
        }
    }

    fun uploadCompanyIDCard(
        fileUri: Uri, file: File,
        uploadCompanyIDCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadCompanyIDCallBack = uploadCompanyIDCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadCompanyIDCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.COMPANY_ID_CARD_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadCompanyIDCard(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.COMPANY_ID_CARD_UPLOAD_API
        )
    }

    fun uploadCompanyBusinessCard(
        fileUri: Uri, file: File,
        uploadCompanyBusinessCardCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadCompanyBusinessCardCallBack = uploadCompanyBusinessCardCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadCompanyBusinessCardCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadCompanyBusinessCard(
                BettrApiSdk.getOrganizationId(),
                description,
                body
            ),
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API
        )
    }

    fun uploadGSTInvoice(
        fileUri: Uri, file: File,
        uploadGSTInvoiceCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadGSTInvoiceCallBack = uploadGSTInvoiceCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadGSTInvoiceCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.GST_INVOICE_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadGSTInvoice(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.GST_INVOICE_UPLOAD_API
        )
    }

    fun uploadGSTIssueCertificate(
        fileUri: Uri, file: File,
        uploadGSTIssueCertificateCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadGSTIssueCertificateCallBack = uploadGSTIssueCertificateCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadGSTIssueCertificateCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadGSTIssueCertificate(
                BettrApiSdk.getOrganizationId(),
                description,
                body
            ),
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API
        )
    }

    fun uploadShopInsidePhoto(
        fileUri: Uri, file: File,
        uploadShopInsideCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadShopInsideCallBack = uploadShopInsideCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadShopInsideCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadShopInside(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API
        )
    }

    fun uploadShopOutsidePhoto(
        fileUri: Uri, file: File,
        uploadShopOutsideCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadShopOutsideCallBack = uploadShopOutsideCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadShopOutsideCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadShopOutside(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API
        )
    }

    fun uploadShopRegistrationPhoto(
        fileUri: Uri, file: File,
        uploadShopRegistrationCallBack: DocumentUploadApiResponseCallback<DocumentUploadResult>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.uploadShopRegistrationCallBack = uploadShopRegistrationCallBack

        val mimeType = ApiSdkFileUtils.getMimeType(fileUri, BettrApiSdk.getApplicationContext())
        if (mimeType == null) {
            uploadShopRegistrationCallBack.onError("No MimeType found")
            return
        }

        // create RequestBody instance from file
        val requestFile = ProgressRequestBody(
            mimeType,
            file,
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API,
            this
        )
        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("fileData", file.name, requestFile)

        val description = RequestBody.create(
            MultipartBody.FORM, "file"
        )
        callApi(
            serviceApi.uploadShopRegistration(BettrApiSdk.getOrganizationId(), description, body),
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API
        )
    }

    fun getBranchDetailsFromIFSC(
        ifscCode: String,
        branchDetailsCallback: ApiResponseCallback<List<BranchDetail>>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.branchDetailsCallBack = branchDetailsCallback
        callApi(
            serviceApi.getBranchDetailsFromIFSC(BettrApiSdk.getOrganizationId(), ifscCode),
            ApiTag.BRANCH_DETAILS_API
        )
    }

    fun getIFSC(
        bankName: String,
        branchName: String,
        cityName: String,
        ifscDetailsCallBack: ApiResponseCallback<List<BankAccountIFSCResponseModel.IFSC>>
    ) {
        if (!BettrApiSdk.isSdkInitialized()) {
            throw IllegalArgumentException(ErrorMessage.SDK_NOT_INITIALIZED_ERROR.value)
        }
        this.ifscDetailsCallBack = ifscDetailsCallBack
        callApi(
            serviceApi.getIFSCFromBankDetails(BettrApiSdk.getOrganizationId(),
                "$bankName $branchName $cityName"
            ),
            ApiTag.IFSC_DETAILS_API
        )
    }

    override fun onApiSuccess(apiTag: ApiTag, response: Any) {
        when (apiTag) {
            ApiTag.GST_INVOICE_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "gst invoice uploaded successfully")
                val gstInvoiceUploadApiResponse = response as DocumentUploadApiResponse
                uploadGSTInvoiceCallBack?.onSuccess(gstInvoiceUploadApiResponse.results!!)
            }
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "gst issue certificate uploaded successfully")
                val gstIssueCertificateUploadApiResponse = response as DocumentUploadApiResponse
                uploadGSTIssueCertificateCallBack?.onSuccess(gstIssueCertificateUploadApiResponse.results!!)
            }
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "shop inside photo uploaded successfully")
                val shopInsideUploadApiResponse = response as DocumentUploadApiResponse
                uploadShopInsideCallBack?.onSuccess(shopInsideUploadApiResponse.results!!)
            }
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "shop outside photo uploaded successfully")
                val shopOutsideUploadApiResponse = response as DocumentUploadApiResponse
                uploadShopOutsideCallBack?.onSuccess(shopOutsideUploadApiResponse.results!!)
            }
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "shop registration photo uploaded successfully")
                val shopRegistrationUploadApiResponse = response as DocumentUploadApiResponse
                uploadShopRegistrationCallBack?.onSuccess(shopRegistrationUploadApiResponse.results!!)
            }
            ApiTag.COMPANY_ID_CARD_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "company id uploaded successfully")
                val companyIdUploadApiResponse = response as DocumentUploadApiResponse
                uploadCompanyIDCallBack?.onSuccess(companyIdUploadApiResponse.results!!)
            }
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "company business card uploaded successfully")
                val companyBusinessCardUploadApiResponse = response as DocumentUploadApiResponse
                uploadCompanyBusinessCardCallBack?.onSuccess(companyBusinessCardUploadApiResponse.results!!)
            }
            ApiTag.UPDATE_LEAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Lead updated successfully")
                val updateLeadApiResponse = response as LeadDetailApiResponse
                decryptLeadData(updateLeadApiResponse.results!!)
                updateLeadCallBack?.onSuccess(updateLeadApiResponse.results!!)
            }
            ApiTag.GET_LEAD_API -> {
                BettrApiSdkLogger.printInfo(TAG, "Lead fetched successfully")
                val getLeadApiResponse = response as LeadDetailApiResponse
                decryptLeadData(getLeadApiResponse.results!!)
                getLeadCallBack?.onSuccess(getLeadApiResponse.results!!)
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
            ApiTag.VERIFY_DOCUMENTS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "documents verified successfully")
                val verifyDocumentsApiResponse = response as VerifyDocumentsApiResponse
                verifyDocumentsCallBack?.onSuccess(verifyDocumentsApiResponse.results!!)
            }
            ApiTag.CHECKLIST_API -> {
                BettrApiSdkLogger.printInfo(TAG, "checklist fetched successfully")
                val checkListApiResponse = response as CheckListApiResponse
                checkListCallBack?.onSuccess(checkListApiResponse.results!!)
            }
            ApiTag.APPLICATION_JOURNEY_CONTENT_API -> {
                BettrApiSdkLogger.printInfo(TAG, "application journey content fetched successfully")
                val applicationJourneyContentResponse =
                    response as ApplicationJourneyContentApiResponse
                applicationJourneyContentCallBack?.onSuccess(applicationJourneyContentResponse.results!!)
            }
            ApiTag.BRANCH_DETAILS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "branch details fetched successfully")
                val branchDetailsApiResponse = response as IFSCCityAndBranchApiResponse
                branchDetailsCallBack?.onSuccess(branchDetailsApiResponse.results!!)
            }
            ApiTag.IFSC_DETAILS_API -> {
                BettrApiSdkLogger.printInfo(TAG, "ifsc details fetched successfully")
                val ifscDetailsApiResponse = response as BankAccountIFSCResponseModel
                ifscDetailsCallBack?.onSuccess(ifscDetailsApiResponse.results!!)
            }
        }
    }

    override fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + errorMessage)
        when (apiTag) {
            ApiTag.GST_INVOICE_UPLOAD_API -> {
                uploadGSTInvoiceCallBack?.onError(errorMessage)
            }
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API -> {
                uploadGSTIssueCertificateCallBack?.onError(errorMessage)
            }
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API -> {
                uploadShopInsideCallBack?.onError(errorMessage)
            }
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API -> {
                uploadShopOutsideCallBack?.onError(errorMessage)
            }
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API -> {
                uploadShopRegistrationCallBack?.onError(errorMessage)
            }
            ApiTag.COMPANY_ID_CARD_UPLOAD_API -> {
                uploadCompanyIDCallBack?.onError(errorMessage)
            }
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API -> {
                uploadCompanyBusinessCardCallBack?.onError(errorMessage)
            }
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.GET_LEAD_API -> {
                getLeadCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(errorCode, errorMessage)
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
            ApiTag.VERIFY_DOCUMENTS_API -> {
                verifyDocumentsCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.CHECKLIST_API -> {
                checkListCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.APPLICATION_JOURNEY_CONTENT_API -> {
                applicationJourneyContentCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.BRANCH_DETAILS_API -> {
                branchDetailsCallBack?.onError(errorCode, errorMessage)
            }
            ApiTag.IFSC_DETAILS_API -> {
                ifscDetailsCallBack?.onError(errorCode, errorMessage)
            }
        }
    }

    override fun onTimeout(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.API_TIMEOUT_ERROR.value)
        when (apiTag) {
            ApiTag.GST_INVOICE_UPLOAD_API -> {
                uploadGSTInvoiceCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API -> {
                uploadGSTIssueCertificateCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API -> {
                uploadShopInsideCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API -> {
                uploadShopOutsideCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API -> {
                uploadShopRegistrationCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.COMPANY_ID_CARD_UPLOAD_API -> {
                uploadCompanyIDCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API -> {
                uploadCompanyBusinessCardCallBack?.onError(ErrorMessage.API_TIMEOUT_ERROR.value)
            }
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.GET_LEAD_API -> {
                getLeadCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
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
            ApiTag.VERIFY_DOCUMENTS_API -> {
                verifyDocumentsCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.CHECKLIST_API -> {
                checkListCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.APPLICATION_JOURNEY_CONTENT_API -> {
                applicationJourneyContentCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.BRANCH_DETAILS_API -> {
                branchDetailsCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value
                )
            }
            ApiTag.IFSC_DETAILS_API -> {
                ifscDetailsCallBack?.onError(NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.API_TIMEOUT_ERROR.value)
            }
        }
    }

    override fun onNetworkError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.NETWORK_ERROR.value)
        when (apiTag) {
            ApiTag.GST_INVOICE_UPLOAD_API -> {
                uploadGSTInvoiceCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API -> {
                uploadGSTIssueCertificateCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API -> {
                uploadShopInsideCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API -> {
                uploadShopOutsideCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API -> {
                uploadShopRegistrationCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.COMPANY_ID_CARD_UPLOAD_API -> {
                uploadCompanyIDCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API -> {
                uploadCompanyBusinessCardCallBack?.onError(ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(NO_NETWORK_ERROR_CODE, ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.GET_LEAD_API -> {
                getLeadCallBack?.onError(NO_NETWORK_ERROR_CODE, ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
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
            ApiTag.VERIFY_DOCUMENTS_API -> {
                verifyDocumentsCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.CHECKLIST_API -> {
                checkListCallBack?.onError(NO_NETWORK_ERROR_CODE, ErrorMessage.NETWORK_ERROR.value)
            }
            ApiTag.APPLICATION_JOURNEY_CONTENT_API -> {
                applicationJourneyContentCallBack?.onError(
                    NO_NETWORK_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.BRANCH_DETAILS_API -> {
                branchDetailsCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value
                )
            }
            ApiTag.IFSC_DETAILS_API -> {
                ifscDetailsCallBack?.onError(NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.NETWORK_ERROR.value)
            }
        }
    }

    override fun onAuthError(apiTag: ApiTag) {
        BettrApiSdkLogger.printInfo(TAG, apiTag.name + " " + ErrorMessage.AUTH_ERROR.value)
        when (apiTag) {
            ApiTag.GST_INVOICE_UPLOAD_API -> {
                uploadGSTInvoiceCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API -> {
                uploadGSTIssueCertificateCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API -> {
                uploadShopInsideCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API -> {
                uploadShopOutsideCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API -> {
                uploadShopRegistrationCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.COMPANY_ID_CARD_UPLOAD_API -> {
                uploadCompanyIDCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API -> {
                uploadCompanyBusinessCardCallBack?.onError(ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.UPDATE_LEAD_API -> {
                updateLeadCallBack?.onError(NOT_SPECIFIED_ERROR_CODE, ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.GET_LEAD_API -> {
                getLeadCallBack?.onError(NOT_SPECIFIED_ERROR_CODE, ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.VALIDATE_PAN_API -> {
                validatePANNumberCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.VALIDATE_PINCODE_API -> {
                validatePincodeCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.BUREAU_STATUS_API -> {
                bureauStatusCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.BUREAU_QUESTION_API -> {
                bureauQuestionCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.BUREAU_ANSWER_API -> {
                bureauAnswerCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
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
            ApiTag.VERIFY_DOCUMENTS_API -> {
                verifyDocumentsCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.CHECKLIST_API -> {
                checkListCallBack?.onError(NOT_SPECIFIED_ERROR_CODE, ErrorMessage.AUTH_ERROR.value)
            }
            ApiTag.APPLICATION_JOURNEY_CONTENT_API -> {
                applicationJourneyContentCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.BRANCH_DETAILS_API -> {
                branchDetailsCallBack?.onError(
                    NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value
                )
            }
            ApiTag.IFSC_DETAILS_API -> {
                ifscDetailsCallBack?.onError(NOT_SPECIFIED_ERROR_CODE,
                    ErrorMessage.AUTH_ERROR.value)
            }
        }
    }

    override fun onProgressUpdate(percentage: Int, apiTag: ApiTag) {
        when (apiTag) {
            ApiTag.GST_INVOICE_UPLOAD_API -> {
                uploadGSTInvoiceCallBack?.progressUpdate(percentage)
            }
            ApiTag.GST_ISSUE_CERTIFICATE_UPLOAD_API -> {
                uploadGSTIssueCertificateCallBack?.progressUpdate(percentage)
            }
            ApiTag.SHOP_INSIDE_PHOTO_UPLOAD_API -> {
                uploadShopInsideCallBack?.progressUpdate(percentage)
            }
            ApiTag.SHOP_OUTSIDE_PHOTO_UPLOAD_API -> {
                uploadShopOutsideCallBack?.progressUpdate(percentage)
            }
            ApiTag.SHOP_REGISTRATION_PHOTO_UPLOAD_API -> {
                uploadShopRegistrationCallBack?.progressUpdate(percentage)
            }
            ApiTag.COMPANY_ID_CARD_UPLOAD_API -> {
                uploadCompanyIDCallBack?.progressUpdate(percentage)
            }
            ApiTag.COMPANY_BUSINESS_CARD_UPLOAD_API -> {
                uploadCompanyBusinessCardCallBack?.progressUpdate(percentage)
            }
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