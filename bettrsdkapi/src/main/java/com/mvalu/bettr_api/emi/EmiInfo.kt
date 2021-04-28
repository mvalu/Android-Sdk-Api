package com.mvalu.bettr_api.emi

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class EmiInfo() : Parcelable {

    @field:Json(name = "id")
    var id: String? = null // Emi id.

    @field:Json(name = "LmsCCTransactionId")
    var transactionId: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null //Principal amount

    @field:Json(name = "roi")
    var roi: Float? = null

    @field:Json(name = "duration")
    var duration: Int? = null

    @field:Json(name = "penaltyAmount")
    var penaltyAmount: Double? = null

    @field:Json(name = "status")
    var status: String? = null // "ACTIVE"

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "totalAmountPayable")
    var totalAmountPayable: Double? = null

    @field:Json(name = "emiAmount")
    var emiAmount: Double? = null

    @field:Json(name = "processingFees")
    var processingFees: Double? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        transactionId = parcel.readString()
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
        roi = parcel.readValue(Float::class.java.classLoader) as? Float
        duration = parcel.readValue(Int::class.java.classLoader) as? Int
        penaltyAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        status = parcel.readString()
        createdAt = parcel.readString()
        totalAmountPayable = parcel.readValue(Double::class.java.classLoader) as? Double
        emiAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        processingFees = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(transactionId)
        parcel.writeValue(amount)
        parcel.writeValue(roi)
        parcel.writeValue(duration)
        parcel.writeValue(penaltyAmount)
        parcel.writeString(status)
        parcel.writeString(createdAt)
        parcel.writeValue(totalAmountPayable)
        parcel.writeValue(emiAmount)
        parcel.writeValue(processingFees)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmiInfo> {
        override fun createFromParcel(parcel: Parcel): EmiInfo {
            return EmiInfo(parcel)
        }

        override fun newArray(size: Int): Array<EmiInfo?> {
            return arrayOfNulls(size)
        }
    }

}