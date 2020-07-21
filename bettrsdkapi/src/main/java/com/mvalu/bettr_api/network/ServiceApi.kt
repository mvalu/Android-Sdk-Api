package com.mvalu.bettr_api.network

import com.mvalu.bettr_api.account_statements.AccountStatementsApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionInfoApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsApiResponse
import com.mvalu.bettr_api.application_journey.LeadDetail
import com.mvalu.bettr_api.application_journey.LeadDetailApiResponse
import com.mvalu.bettr_api.application_journey.bureau.*
import com.mvalu.bettr_api.application_journey.documents.DocumentUploadApiResponse
import com.mvalu.bettr_api.application_journey.documents.VerifyDocumentsApiResponse
import com.mvalu.bettr_api.application_journey.documents.VerifyDocumentsRequest
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberApiResponse
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberRequest
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeApiResponse
import com.mvalu.bettr_api.home_module.HomeModuleApiResponse
import com.mvalu.bettr_api.login.GenerateTokenRequest
import com.mvalu.bettr_api.login.GenerateTokenResponse
import com.mvalu.bettr_api.transactions.CardTransactionsApiResponse
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

    @GET("v1/{organizationId}/cc/account/{accountId}/statementTransaction")
    fun getStatementTransactions(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Query("type") type: String,
        @Query("startMonth") startMonth: String,
        @Query("endMonth") endMonth: String,
        @Query("status") status: String,
        @Query("amountStart") amountStart: Int,
        @Query("amountEnd") amountEnd: Int,
        @Query("category") category: String
    ): Observable<Response<CardTransactionsApiResponse>>

    @GET("v1/{organizationId}/cc/account/{accountId}/statement")
    fun getAccountStatements(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String
    ): Observable<Response<AccountStatementsApiResponse>>

    @GET("v1/{organizationId}/cc/account/{accountId}/statement/{statementId}")
    fun getAccountStatementTransactions(
        @Path("organizationId") organizationId: String,
        @Path("accountId") accountId: String,
        @Path("statementId") statementId: String
    ): Observable<Response<AccountStatementTransactionsApiResponse>>

    @GET("v1/{organizationId}/cc/account/{accountId}/statementTransaction/{statementTransactionId}")
    fun getAccountStatementTransactionInfo(
        @Path("organizationId") organizationId: String,
        @Path("statementTransactionId") statementTransactionId: String
    ): Observable<Response<AccountStatementTransactionInfoApiResponse>>

    @PUT("v1/{organizationId}/leads/{leadId}")
    fun updateLead(
        @Path("organizationId") organizationId: String,
        @Path("leadId") leadId: String,
        @Body lead: LeadDetail?
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
}