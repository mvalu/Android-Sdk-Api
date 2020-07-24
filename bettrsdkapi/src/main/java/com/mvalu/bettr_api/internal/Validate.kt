package com.mvalu.bettr_api.internal

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.mvalu.bettr_api.utils.BettrApiSdkLogger

object Validate {

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

    fun hasStoragePermissions(context: Context, shouldThrow: Boolean) {
        notNull(context, "Application context")
        if (context.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw IllegalStateException("Storage permission required")
            } else {
                BettrApiSdkLogger.printError(TAG, "Storage permission required")
            }
        }
    }

    fun hasLocationPermissions(context: Context, shouldThrow: Boolean) {
        notNull(context, "Application context")
        if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw IllegalStateException("Fine Location permission required")
            } else {
                BettrApiSdkLogger.printError(TAG, "Fine Location permission required")
            }
        } else if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw IllegalStateException("Coarse Location permission required")
            } else {
                BettrApiSdkLogger.printError(TAG, "Coarse Location permission required")
            }
        }
    }

    fun hasCameraPermissions(context: Context, shouldThrow: Boolean) {
        notNull(context, "Application context")
        if (context.checkCallingOrSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            if (shouldThrow) {
                throw IllegalStateException("Camera permission required")
            } else {
                BettrApiSdkLogger.printError(TAG, "Camera permission required")
            }
        }
    }
}