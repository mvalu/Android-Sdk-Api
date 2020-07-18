package com.mvalu.bettr_api.network

import com.mvalu.bettr_api.account_statements.AccountStatementsApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionInfoApiResponse
import com.mvalu.bettr_api.account_statements.transactions.AccountStatementTransactionsApiResponse
import com.mvalu.bettr_api.application_journey.LeadDetail
import com.mvalu.bettr_api.application_journey.LeadDetailApiResponse
import com.mvalu.bettr_api.application_journey.bureau.BureauStatusApiResponse
import com.mvalu.bettr_api.application_journey.bureau.BureauStatusRequest
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberApiResponse
import com.mvalu.bettr_api.application_journey.pan.ValidatePANNumberRequest
import com.mvalu.bettr_api.application_journey.pincode.ValidatePincodeApiResponse
import com.mvalu.bettr_api.home_module.HomeModuleApiResponse
import com.mvalu.bettr_api.login.GenerateTokenRequest
import com.mvalu.bettr_api.login.GenerateTokenResponse
import com.mvalu.bettr_api.transactions.CardTransactionsApiResponse
import io.reactivex.Observable
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

    @PUT("v1/{organizationId}/cc/leads/{leadId}")
    fun updateLead(
        @Path("organizationId") organizationId: String,
        @Path("leadId") leadId: String,
        @Body lead: LeadDetail?
    ): Observable<Response<LeadDetailApiResponse>>

    @POST("v1/{organizationId}/cc/users/panValidate")
    fun validatePANNumber(
        @Path("organizationId") organizationId: String,
        @Body validatePANRequestModel: ValidatePANNumberRequest
    ): Observable<Response<ValidatePANNumberApiResponse>>

    @GET("v1/{organizationId}/cc/leads/pinCheck/{pincode}")
    fun validatePincode(
        @Path("organizationId") organizationId: String,
        @Path("pincode") pinCode: String
    ): Observable<Response<ValidatePincodeApiResponse>>

    @POST("v1/{organizationId}/cc/integration/bureau")
    fun checkBureauStatus(
        @Path("organizationId") organizationId: String,
        @Body bureauStatusRequest: BureauStatusRequest
    ): Observable<Response<BureauStatusApiResponse>>
}