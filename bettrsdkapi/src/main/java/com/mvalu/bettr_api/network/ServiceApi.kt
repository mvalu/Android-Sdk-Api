package com.mvalu.bettr_api.network

import com.mvalu.bettr_api.home_module.HomeModuleApiResponse
import com.mvalu.bettr_api.login.GenerateTokenRequest
import com.mvalu.bettr_api.login.GenerateTokenResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ServiceApi {
    @POST("v1/{organizationId}/auth/generateToken")
    fun generateToken(
        @Path("organizationId") organizationId: String,
        @Body generateTokenRequest: GenerateTokenRequest
    ): Observable<Response<GenerateTokenResponse>>

    @GET("v1/{organizationId}/rm/cc_home_module")
    fun getCardHomeModule(@Path("organizationId") organizationId: String): Observable<Response<HomeModuleApiResponse>>
}