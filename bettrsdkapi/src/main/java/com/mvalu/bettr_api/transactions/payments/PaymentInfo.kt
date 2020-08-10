package com.mvalu.bettr_api.transactions.payments

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class PaymentInfo() : Parcelable {

    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "source")
    var source: String? = null

    @field:Json(name = "description")
    var description: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "LmsCCAccountId")
    var lmsCCAccountId: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "surplusAmount")
    var surplusAmount: Double? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
        source = parcel.readString()
        description = parcel.readString()
        customerUserId = parcel.readString()
        organizationId = parcel.readString()
        lmsCCAccountId = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
        status = parcel.readString()
        surplusAmount = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeValue(amount)
        parcel.writeString(source)
        parcel.writeString(description)
        parcel.writeString(customerUserId)
        parcel.writeString(organizationId)
        parcel.writeString(lmsCCAccountId)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
        parcel.writeString(status)
        parcel.writeValue(surplusAmount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PaymentInfo> {
        override fun createFromParcel(parcel: Parcel): PaymentInfo {
            return PaymentInfo(parcel)
        }

        override fun newArray(size: Int): Array<PaymentInfo?> {
            return arrayOfNulls(size)
        }
    }
}