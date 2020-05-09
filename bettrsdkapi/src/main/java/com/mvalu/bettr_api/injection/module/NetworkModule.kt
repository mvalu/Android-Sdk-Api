package com.mvalu.bettr_api.injection.module

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.BuildConfig
import com.mvalu.bettr_api.network.ServiceApi
import com.mvalu.bettr_api.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor {
            val request = it.request().newBuilder()
                .addHeader("version-code", BuildConfig.VERSION_CODE.toString())
                .addHeader("type", "ANDROID")
                .addHeader("x-client-tenant-type", "CUSTOMER")
                .addHeader(
                    "x-access-key",
                    BettrApiSdk.getAccessKey()
                )
                .addHeader(
                    "x-signed-key",
                    BettrApiSdk.getSignedSecretKey()
                )
                .build()
            it.proceed(request)
        }

        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        clientBuilder.readTimeout(3 * 60, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        clientBuilder.addInterceptor(headerInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(clientBuilder.build())
            .build()
    }
}