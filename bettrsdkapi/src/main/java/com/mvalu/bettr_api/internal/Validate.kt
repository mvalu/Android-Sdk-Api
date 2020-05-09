package com.mvalu.bettr_api.internal

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

internal object Validate {

    private const val TAG = "Validation"

    fun notNull(arg: Any?, name: String) {
        if (arg == null) {
            throw NullPointerException("Argument '$name' cannot be null")
        }
    }

    fun notNullorEmpty(arg: Any?, name: String) {
        if (arg == null) {
            throw NullPointerException("Argument '$name' cannot be null")
        } else if (arg is String && (arg as String).isEmpty()) {
            throw NullPointerException("Argument '$name' cannot be empty")
        }
    }

    fun hasInternetPermissions(context: Context, shouldThrow: Boolean) {
        notNull(context, "Application context")
        if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw IllegalStateException("Internet permission required")
            } else {
                BettrApiSdkLogger.printError(TAG, "Internet permission required")
            }
        }
    }

    fun hasReadPhoneStatePermissions(context: Context, shouldThrow: Boolean) {
        notNull(context, "Application context")
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw IllegalStateException("Read phone state permission required")
            } else {
                BettrApiSdkLogger.printError(TAG, "Read phone state permission required")
            }
        }
    }
}