package com.mvalu.bettr_api.home_module

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class DueInfo() : Parcelable {
    @field:Json(name = "descriptionText")
    var descriptionText: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "totalDue")
    var totalDue: Double? = null

    @field:Json(name = "totalFine")
    var totalFine: Double? = null

    @field:Json(name = "totalSpend")
    var totalSpend: Double? = null

    @field:Json(name = "paymentButton")
    var showPaymentButton: Boolean? = false

    constructor(parcel: Parcel) : this() {
        descriptionText = parcel.readString()
        status = parcel.readString()
        totalDue = parcel.readValue(Double::class.java.classLoader) as? Double
        totalFine = parcel.readValue(Double::class.java.classLoader) as? Double
        totalSpend = parcel.readValue(Double::class.java.classLoader) as? Double
        showPaymentButton = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(descriptionText)
        parcel.writeString(status)
        parcel.writeValue(totalDue)
        parcel.writeValue(totalFine)
        parcel.writeValue(totalSpend)
        parcel.writeValue(showPaymentButton)
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