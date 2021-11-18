package com.mvalu.bettr_api.application_journey

enum class VerifyKycStatus(val value: String?) {
/*Values for doc upload (pan/ selfie/ okyc)*/
    APPROVED("APPROVED"),
    INPROCESS("INPROCESS"),
    FAILED("FAILED")
}