package com.mvalu.bettr_api.home_module

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class StatementSummary() : Parcelable {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "paymentStatus")
    var paymentStatus: String? = null

    @field:Json(name = "startDate")
    var startDate: String? = null

    @field:Json(name = "endDate")
    var endDate: String? = null

    @field:Json(name = "spendCount")
    var spendCount: Int? = null

    @field:Json(name = "currentBalance")
    var currentBalance: Float? = null

    @field:Json(name = "startingBalance")
    var startingBalance: Float? = null

    @field:Json(name = "endingBalance")
    var endingBalance: Float? = null

    @field:Json(name = "lateFees")
    var lateFees: Float? = null

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

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        paymentStatus = parcel.readString()
        startDate = parcel.readString()
        endDate = parcel.readString()
        spendCount = parcel.readValue(Int::class.java.classLoader) as? Int
        currentBalance = parcel.readValue(Float::class.java.classLoader) as? Float
        startingBalance = parcel.readValue(Float::class.java.classLoader) as? Float
        endingBalance = parcel.readValue(Float::class.java.classLoader) as? Float
        lateFees = parcel.readValue(Float::class.java.classLoader) as? Float
        customerUserId = parcel.readString()
        organizationId = parcel.readString()
        lmsCCAccountId = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(paymentStatus)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeValue(spendCount)
        parcel.writeValue(currentBalance)
        parcel.writeValue(startingBalance)
        parcel.writeValue(endingBalance)
        parcel.writeValue(lateFees)
        parcel.writeString(customerUserId)
        parcel.writeString(organizationId)
        parcel.writeString(lmsCCAccountId)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementSummary> {
        override fun createFromParcel(parcel: Parcel): StatementSummary {
            return StatementSummary(parcel)
        }

        override fun newArray(size: Int): Array<StatementSummary?> {
            return arrayOfNulls(size)
        }
    }
}