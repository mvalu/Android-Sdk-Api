package com.mvalu.androidsdkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.BettrApiSdkCallback
import com.mvalu.bettr_api.card_user.CardUser

class MainActivity : AppCompatActivity(), BettrApiSdkCallback {
    override fun onSuccess() {
        Toast.makeText(applicationContext,"Sdk initialized", Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: String) {
        Toast.makeText(applicationContext,error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BettrApiSdk.enableLoggingBehaviour(true)
        BettrApiSdk.initializeSDK(applicationContext,
            "712ef65f-7b43-4240-8577-6e89f3bcbdcd",
            "97ec49fc-f226-47dc-8fb9-1e0cfe227b2f",
            "6f7016b5-ef39-4c1e-a6ce-dd069fb96c01",
            "123",
            null,
            this)

    }
}
