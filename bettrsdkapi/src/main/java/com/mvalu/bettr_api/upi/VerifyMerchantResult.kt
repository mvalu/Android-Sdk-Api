package com.mvalu.bettr_api.upi

import android.os.Parcel
import android.os.Parcelable
import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class VerifyMerchantResult() : Parcelable {
    @field:Json(name = "upiMerchantId")
    var upiMerchantId: String? = null

    @field:Json(name = "merchantName")
    var merchantName: String? = null

    @field:Json(name = "merchantVpa")
    var merchantVpa: String? = null

    @field:Json(name = "category")
    var category: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "LmsCcAccount")
    var lmsCcAccount: AccountInfo? = null

    constructor(parcel: Parcel) : this() {
        upiMerchantId = parcel.readString()
        merchantName = parcel.readString()
        merchantVpa = parcel.readString()
        category = parcel.readString()
        createdAt = parcel.readString()
        lmsCcAccount = parcel.readParcelable(AccountInfo::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(upiMerchantId)
        parcel.writeString(merchantName)
        parcel.writeString(merchantVpa)
        parcel.writeString(category)
        parcel.writeString(createdAt)
        parcel.writeParcelable(lmsCcAccount, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VerifyMerchantResult> {
        override fun createFromParcel(parcel: Parcel): VerifyMerchantResult {
            return VerifyMerchantResult(parcel)
        }

        override fun newArray(size: Int): Array<VerifyMerchantResult?> {
            return arrayOfNulls(size)
        }
    }
}