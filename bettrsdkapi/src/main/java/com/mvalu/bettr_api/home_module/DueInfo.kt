package com.mvalu.bettr_api.home_module

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class DueInfo() : Parcelable {
    @field:Json(name = "descriptionText")
    var descriptionText: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    constructor(parcel: Parcel) : this() {
        descriptionText = parcel.readString()
        status = parcel.readString()
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(descriptionText)
        parcel.writeString(status)
        parcel.writeValue(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DueInfo> {
        override fun createFromParcel(parcel: Parcel): DueInfo {
            return DueInfo(parcel)
        }

        override fun newArray(size: Int): Array<DueInfo?> {
            return arrayOfNulls(size)
        }
    }
}