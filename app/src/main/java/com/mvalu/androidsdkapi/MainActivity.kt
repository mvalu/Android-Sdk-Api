package com.mvalu.androidsdkapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.BettrApiSdkCallback
import com.mvalu.bettr_api.home_module.HomeModule
import com.mvalu.bettr_api.home_module.HomeModuleDetails
import com.mvalu.bettr_api.network.ApiResponseCallback

class MainActivity : AppCompatActivity(), BettrApiSdkCallback {
    override fun onSuccess() {
        Toast.makeText(applicationContext, "Sdk initialized", Toast.LENGTH_SHORT).show()
        HomeModule.getHomeModuleDetails(object : ApiResponseCallback<List<HomeModuleDetails>> {
            override fun onSuccess(response: List<HomeModuleDetails>) {
                Toast.makeText(applicationContext, response.get(0).id, Toast.LENGTH_SHORT).show()
            }

            override fun onError(errorMessage: String) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BettrApiSdk.enableLoggingBehaviour(true)
        BettrApiSdk.initializeSDK(
            applicationContext,
            "712ef65f-7b43-4240-8577-6e89f3bcbdcd",
            "97ec49fc-f226-47dc-8fb9-1e0cfe227b2f",
            "6f7016b5-ef39-4c1e-a6ce-dd069fb96c01",
            "43",
            null,
            this
        )

//        CardUser.getUserDetails(CardUserRequest(), object : ApiResponseCallback<CardUserResponse>{
//            override fun onSuccess(response: CardUserResponse) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onError(errorMessage: String) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })

    }
}
