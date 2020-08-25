package com.mvalu.bettr_api.network

import com.mvalu.bettr_api.account_statements.AccountStatementsApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionInfoApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsApiResponse
import com.mvalu.bettr_api.application_journey.LeadDetail
import com.mvalu.bettr_api.application_journey.LeadDetailApiResponse
import com.mvalu.bettr_api.application_journey.bureau.*
import com.mvalu.bettr_api.application_journey.checklist.CheckListApiResponse
import com.mvalu.bettr_api.application_journey.checklist.CheckListRequest
import com.mvalu.bettr_api.application_journey.content.ApplicationJourneyContentApiResponse
import com.mvalu.bettr_api.application_journey.content.ApplicationJourneyContentRequest
import com.mvalu.bettr_api.application_journey.documents.DocumentUploadApiResponse
import com.mvalu.bettr_api.application_journey.documents.VerifyDocumentsApiResponse
import com.mvalu.bettr_api.application_journey.documents.VerifyDocumentsRequest
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberApiResponse
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberRequest
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeApiResponse
import com.mvalu.bettr_api.downloads.DocumentDownloadApiResponse
import com.mvalu.bettr_api.home_module.HomeModuleApiResponse
import com.mvalu.bettr_api.home_module.statement.HomeModuleStatementApiResponse
import com.mvalu.bettr_api.login.GenerateTokenRequest
import com.mvalu.bettr_api.login.GenerateTokenResponse
import com.mvalu.bettr_api.payment.GenerateOrderApiRequest
import com.mvalu.bettr_api.payment.GenerateOrderApiResponse
import com.mvalu.bettr_api.payment.PaymentStatusApiResponse
import com.mvalu.bettr_api.payment.PaymentStatusRequest
import com.mvalu.bettr_api.payment.detail.PaymentDetailApiResponse
import com.mvalu.bettr_api.payment.summary.PaymentSummaryApiResponse
import com.mvalu.bettr_api.rewards.RewardPointsSummaryApiResponse
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackApiResponse
import com.mvalu.bettr_api.rewards.cashback.RewardCashbackInfoApiResponse
import com.mvalu.bettr_api.settings.*
import com.mvalu.bettr_api.settings.otp.OtpApiResponse
import com.mvalu.bettr_api.settings.otp.OtpReSendRequest
import com.mvalu.bettr_api.settings.otp.OtpSendRequest
import com.mvalu.bettr_api.settings.otp.OtpVerifyRequest
import com.mvalu.bettr_api.settings.plastic_card.CardNumberVerifyRequest
import com.mvalu.bettr_api.transactions.AccountTransactionsApiResponse
import com.mvalu.bettr_api.transactions.CardTransactionsApiResponse
import com.mvalu.bettr_api.transactions.TransactionAnalysisApiResponse
import com.mvalu.bettr_api.transactions.TransactionInfoApiResponse
import com.mvalu.bettr_api.transactions.payments.CardPaymentsApiResponse
import com.mvalu.bettr_api.transactions.payments.PaymentInfoApiResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ServiceApi {
    @POST("v1/{organizationId}/auth/generateToken")
    fun generateToken(
        @Path("organizationId") organizationId: String,
        @Body generateTokenRequest: GenerateTokenRequest
    ): Observable<Response<GenerateTokenResponse>>

    @GET("v1/{organizationId}/rm/cc_home_module")
    fun getCardHomeModule(@Path("organizationId") organizationId: String): Observable<Response<HomeModuleApiResponse>>

    @GET("v1/{organizationId}/rm/cc_statement_module")
    fun getCardHomeModuleStatement(@Path("organizationId") organizationId: String): Observable<Response<HomeModuleStatementApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/transaction")
    fun getAccountTransactions(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("startMonth") startMonth: String?,
        @Query("endMonth") endMonth: String?,
        @Query("amountStart") amountStart: String?,
        @Query("amountEnd") amountEnd: String?,
        @Query("merchantCategory") merchantCategory: String?,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?,
        @Query("status") status: String?,
        @Query("search") search: String?,
        @Query("offset") offset: Int
    ): Observable<Response<AccountTransactionsApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/spendAnalysis")
    fun getTransactionsAnalysis(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Observable<Response<TransactionAnalysisApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/payment")
    fun getCardPayments(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("source") source: String?,
        @Query("startMonth") startMonth: String?,
        @Query("endMonth") endMonth: String?,
        @Query("status") status: String?,
        @Query("offset") offset: Int
    ): Observable<Response<CardPaymentsApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/statementTransaction")
    fun getStatementTransactions(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("type") type: String?,
        @Query("startMonth") startMonth: String?,
        @Query("endMonth") endMonth: String?,
        @Query("status") status: String?,
        @Query("amountStart") amountStart: Int?,
        @Query("amountEnd") amountEnd: Int?,
        @Query("category") category: String?
    ): Observable<Response<CardTransactionsApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/statement")
    fun getAccountStatements(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String
    ): Observable<Response<AccountStatementsApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/statement/{statementId}")
    fun getAccountStatementTransactions(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("statementId") statementId: String
    ): Observable<Response<AccountStatementTransactionsApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/statementTransaction/{statementTransactionId}")
    fun getAccountStatementTransactionInfo(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("statementTransactionId") statementTransactionId: String
    ): Observable<Response<AccountStatementTransactionInfoApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/payment/{paymentId}")
    fun getPaymentInfo(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("paymentId") paymentId: String
    ): Observable<Response<PaymentInfoApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/transaction/{transactionId}")
    fun getTransactionInfo(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("transactionId") transactionId: String
    ): Observable<Response<TransactionInfoApiResponse>>

    @PUT("v1/{organizationId}/leads/{leadId}")
    fun updateLead(
        @Path("organizationId") organizationId: String,
        @Path("leadId") leadId: String,
        @Body lead: LeadDetail?
    ): Observable<Response<LeadDetailApiResponse>>

    @GET("v1/{organizationId}/leads/{leadId}")
    fun getLead(
        @Path("organizationId") organizationId: String,
        @Path("leadId") leadId: String
    ): Observable<Response<LeadDetailApiResponse>>

    @POST("v1/{organizationId}/users/panValidate")
    fun validatePANNumber(
        @Path("organizationId") organizationId: String,
        @Body validatePANRequestModel: ValidatePANNumberRequest
    ): Observable<Response<ValidatePANNumberApiResponse>>

    @GET("v1/{organizationId}/leads/pinCheck/{pincode}")
    fun validatePincode(
        @Path("organizationId") organizationId: String,
        @Path("pincode") pinCode: String
    ): Observable<Response<ValidatePincodeApiResponse>>

    @POST("v1/{organizationId}/integration/bureau")
    fun checkBureauStatus(
        @Path("organizationId") organizationId: String,
        @Body bureauStatusRequest: BureauStatusRequest
    ): Observable<Response<BureauStatusApiResponse>>

    @POST("v1/{organizationId}/integration/bureau/generateQuestion")
    fun getBureauQuestion(
        @Path("organizationId") organizationId: String,
        @Body bureauQuestionRequest: BureauQuestionRequest
    ): Observable<Response<BureauQuestionApiResponse>>

    @POST("v1/{organizationId}/integration/bureau/verifyAnswer")
    fun bureauVerifyAnswer(
        @Path("organizationId") organizationId: String,
        @Body bureauAnswerRequest: BureauAnswerRequest
    ): Observable<Response<BureauStatusApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/idProof")
    fun uploadIdProof(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/addressProof")
    fun uploadAddressProof(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/bankStatement")
    fun uploadBankStatement(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/salarySlip")
    fun uploadSalarySlip(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/photo")
    fun uploadProfilePic(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/pan")
    fun uploadPanCard(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/aadharCard")
    fun uploadAadharFront(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @Multipart
    @POST("v1/{organizationId}/upload/single/aadharCardBack")
    fun uploadAadharBack(
        @Path("organizationId") organizationId: String,
        @Part("fileData") description: RequestBody,
        @Part file: MultipartBody.Part
    ): Observable<Response<DocumentUploadApiResponse>>

    @POST("v1/{organizationId}/application/{applicationId}/leadDocumentVerify")
    fun verifyDocuments(
        @Path("organizationId") organizationId: String,
        @Path("applicationId") applicationId: String,
        @Body request: VerifyDocumentsRequest
    ): Observable<Response<VerifyDocumentsApiResponse>>

    @POST("v1/{organizationId}/leads/screenDetail")
    fun fetchCheckList(
        @Path("organizationId") organizationId: String,
        @Body request: CheckListRequest
    ): Observable<Response<CheckListApiResponse>>

    @POST("v1/{organizationId}/constants/cc-dropdown")
    fun fetchApplicationJourneyContent(
        @Path("organizationId") organizationId: String,
        @Body request: ApplicationJourneyContentRequest
    ): Observable<Response<ApplicationJourneyContentApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/statement/{statementId}?isDownload=true")
    fun getStatementDownloadLink(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("statementId") statementId: String
    ): Observable<Response<DocumentDownloadApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/reward_point")
    fun getRewardPoints(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("startMonth") startMonth: String?,
        @Query("endMonth") endMonth: String?,
        @Query("pointStart") pointStart: String?,
        @Query("pointEnd") pointEnd: String?,
        @Query("startDate") startDate: Int?,
        @Query("endDate") endDate: Int?,
        @Query("search") search: String?
    ): Observable<Response<CardTransactionsApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/reward_cashback")
    fun getRewardCashbacks(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("startMonth") startMonth: String?,
        @Query("endMonth") endMonth: String?,
        @Query("amountStart") pointStart: String?,
        @Query("amountEnd") pointEnd: String?,
        @Query("startDate") startDate: Int?,
        @Query("endDate") endDate: Int?,
        @Query("search") search: String?
    ): Observable<Response<RewardCashbackApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/reward_point/rewardPointSummary")
    fun getRewardPointsSummary(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String
    ): Observable<Response<RewardPointsSummaryApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/reward_cashback/{rewardCashbackId}")
    fun getRewardCashbackInfo(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("rewardCashbackId") rewardCashbackId: String
    ): Observable<Response<RewardCashbackInfoApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/payment/quickPaymentSummary")
    fun getPaymentSummary(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String
    ): Observable<Response<PaymentSummaryApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}")
    fun getSettingsInfo(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String
    ): Observable<Response<SettingsInfoApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/payment/generateOrderId")
    fun generateOrderId(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Body generateOrderRequest: GenerateOrderApiRequest
    ): Observable<Response<GenerateOrderApiResponse>>

    @POST("v1/{organizationId}/integration/razorpay/paymentStatus")
    fun checkPaymentStatus(
        @Path("organizationId") organizationId: String,
        @Body checkPaymentStatusRequest: PaymentStatusRequest
    ): Observable<Response<PaymentStatusApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}/image")
    fun getCardImage(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String
    ): Observable<Response<CardImageApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}/pinInitialized")
    fun initializeCardPin(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String
    ): Observable<Response<PinInitApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}/pinSet")
    fun setCardPin(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String,
        @Body pinSetReq: PinSetRequest
    ): Observable<Response<SettingsGenericApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/card/otpRequest")
    fun sendOtp(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Body otpSendRequest: OtpSendRequest
    ): Observable<Response<OtpApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/card/otpResend")
    fun resendOtp(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Body otpReSendRequest: OtpReSendRequest
    ): Observable<Response<OtpApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/card/otpVerification")
    fun verifyOtp(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Body otpVerifyRequest: OtpVerifyRequest
    ): Observable<Response<OtpApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/payment/paymentWithCardDetail/{paymentId}/")
    fun getPaymentDetails(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("paymentId") paymentId: String
    ): Observable<Response<PaymentDetailApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}/block")
    fun blockCard(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String
    ): Observable<Response<BlockCardApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/card/activateDigitalCard")
    fun activateDigitalCard(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String
    ): Observable<Response<SettingsGenericApiResponse>>

    @GET("v1/{organizationId}/lms/cc/account/{accountId}/card/activatePlasticCard")
    fun activatePlasticCard(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String
    ): Observable<Response<SettingsGenericApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}/verifyCardNumber")
    fun verifyCardNumber(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String,
        @Body cardNumberVerifyRequest: CardNumberVerifyRequest
    ): Observable<Response<SettingsGenericApiResponse>>

    @POST("v1/{organizationId}/lms/cc/account/{accountId}/card/{cardId}/actionSwitch")
    fun cardOnOff(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("cardId") cardId: String,
        @Body cardOnOffRequest: CardOnOffRequest
    ): Observable<Response<BlockCardApiResponse>>
}