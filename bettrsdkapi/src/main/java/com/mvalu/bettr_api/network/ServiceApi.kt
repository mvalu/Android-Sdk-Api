package com.mvalu.bettr_api.network

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
        @Query("amountEnd") amountEnd: Int
    ): Observable<Response<CardTransactionsApiResponse>>
}