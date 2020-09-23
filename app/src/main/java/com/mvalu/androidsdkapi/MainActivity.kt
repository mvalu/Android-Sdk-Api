package com.mvalu.androidsdkapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvalu.bettr_api.BettrApiSdkCallback

class MainActivity : AppCompatActivity(), BettrApiSdkCallback {
    override fun onSuccess() {
    }

    override fun onError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
