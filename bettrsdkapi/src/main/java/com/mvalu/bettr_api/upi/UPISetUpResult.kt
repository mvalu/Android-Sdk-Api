package com.mvalu.bettr_api.upi

import android.os.Parcel
import android.os.Parcelable
import com.mvalu.bettr_api.home_module.AccountInfo
import com.squareup.moshi.Json

class UPISetUpResult() : Parcelable {
    @field:Json(name = "accountInfo")
    var accountInfo: AccountInfo? = null

    constructor(parcel: Parcel) : this() {
        accountInfo = parcel.readParcelable(AccountInfo::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(accountInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UPISetUpResult> {
        override fun createFromParcel(parcel: Parcel): UPISetUpResult {
            return UPISetUpResult(parcel)
        }

        override fun newArray(size: Int): Array<UPISetUpResult?> {
            return arrayOfNulls(size)
        }
    }
}