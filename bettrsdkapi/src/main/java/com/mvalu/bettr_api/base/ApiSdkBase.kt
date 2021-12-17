package com.mvalu.bettr_api.base

import com.mvalu.bettr_api.network.ApiTag
import com.mvalu.bettr_api.network.ServiceApi
import com.mvalu.bettr_api.utils.BettrApiSdkLogger
import com.mvalu.bettr_api.utils.NOT_SPECIFIED_ERROR_CODE
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

abstract class ApiSdkBase {
    @Inject
    lateinit var serviceApi: ServiceApi

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun callApi(observable: Observable<out Any>, apiTag: ApiTag): Disposable {
        var disposable = observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response: Any? ->
                    @Suppress("UNCHECKED_CAST")
                    var httpResponse = response as Response<Any>
                    BettrApiSdkLogger.printInfo(httpResponse.code().toString(), apiTag.name)
                    if (httpResponse.code() == 200) {
                        onApiSuccess(apiTag, httpResponse.body()!!)
                    } else {
                        var errorResponse = httpResponse.errorBody()
                        when (val errorCode = httpResponse.code()) {
                            401 -> {
                                onAuthError(apiTag)
                            }
                            400 -> {
                                onApiError(errorCode, apiTag, getErrorMessage400(errorResponse!!))
                            }
                            500 -> {
                                onApiError(errorCode, apiTag, getErrorMessage(errorResponse!!))
                            }
                            else -> {
                                onApiError(errorCode, apiTag, getErrorMessage(errorResponse!!))
                            }
                        }
                    }

                },
                { error: Throwable? ->
                    when (error) {
                        is HttpException -> {
                            onApiError(
                                NOT_SPECIFIED_ERROR_CODE,
                                apiTag,
                                getErrorMessage(error.response()?.errorBody()!!)
                            )
                        }
                        is SocketTimeoutException -> {
                            onTimeout(apiTag)
                        }
                        is IOException -> {
                            onNetworkError(apiTag)
                        }
                        else -> {
                            onApiError(NOT_SPECIFIED_ERROR_CODE, apiTag, error?.message!!)
                        }
                    }
                }
            )
        compositeDisposable.add(disposable)
        return disposable
    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        var message: String? = null
        return try {
            message = responseBody.string() //once you perform .string(), that value gets removed from responseBody object.
            val jsonObject = JSONObject(message)
            val errorObject = jsonObject.getJSONObject("error")
            errorObject.getString("message")
        } catch (e: Exception) {
            if (message.isNullOrBlank()) e.message!! else message
        }
    }

    private fun getErrorMessage400(responseBody: ResponseBody): String {
        return try {
            var message: String? = null
            val jsonObject = JSONObject(responseBody.string())
            val errorObject = jsonObject.getJSONObject("error")
            val errorArray = errorObject.optJSONArray("message")
            if(errorArray != null){
                for (i in 0 until errorArray.length()) {
                    val item = errorArray.getJSONObject(i)
                    val arr = item.getJSONArray("messages")
                    message = if (message == null) {
                        arr[0].toString()
                    } else {
                        message + ", " + arr[0].toString()
                    }
                }
                message ?: ""
            } else {
                errorObject.optString("message")
            }

        } catch (e: Exception) {
            e.message!!
        }
    }

    //Api callback methods
    abstract fun onApiSuccess(apiTag: ApiTag, response: Any)

    abstract fun onApiError(errorCode: Int, apiTag: ApiTag, errorMessage: String)
    abstract fun onTimeout(apiTag: ApiTag)
    abstract fun onNetworkError(apiTag: ApiTag)
    abstract fun onAuthError(apiTag: ApiTag)
}