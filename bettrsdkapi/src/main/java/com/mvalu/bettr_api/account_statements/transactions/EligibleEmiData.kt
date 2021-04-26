package com.mvalu.bettr_api.account_statements.transactions

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class EligibleEmiData() : Parcelable {

    @field:Json(name = "duration")
    var duration: Int? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "roi")
    var roi: Float? = null

    @field:Json(name = "totalAmount")
    var totalAmount: Double? = null

    @field:Json(name = "processingFees")
    var processingFees: Double? = null

    constructor(parcel: Parcel) : this() {
        duration = parcel.readValue(Int::class.java.classLoader) as? Int
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
        roi = parcel.readValue(Float::class.java.classLoader) as? Float
        totalAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        processingFees = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(duration)
        parcel.writeValue(amount)
        parcel.writeValue(roi)
        parcel.writeValue(totalAmount)
        parcel.writeValue(processingFees)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EligibleEmiData> {
        override fun createFromParcel(parcel: Parcel): EligibleEmiData {
            return EligibleEmiData(parcel)
        }

        override fun newArray(size: Int): Array<EligibleEmiData?> {
            return arrayOfNulls(size)
        }
    }

}