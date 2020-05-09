package com.mvalu.bettr_api

import com.mvalu.bettr_api.network.ResponseCallback

class Test {
    fun test(){
        BettrApiSdk.getDetails(
            object : ResponseCallback<TestModel> {
                override fun onSuccess(response: TestModel) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
        )
    }
}