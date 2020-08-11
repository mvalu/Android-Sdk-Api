package com.mvalu.bettr_api.account_statements.transactions

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class StatementTransactionInfo() : Parcelable {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "transactionAmount")
    var transactionAmount: Double? = null

    @field:Json(name = "transactionType")
    var transactionType: String? = null

    @field:Json(name = "merchantName")
    var merchantName: String? = null

    @field:Json(name = "merchantCategory")
    var merchantCategory: String? = null

    @field:Json(name = "merchantSubCategory")
    var merchantSubCategory: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "LmsCCAccountId")
    var lmsCCAccountId: String? = null

    @field:Json(name = "LmsCCCardId")
    var lmsCCCardId: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null

    @field:Json(name = "transactionDate")
    var transactionDate: String? = null

    @field:Json(name = "status")
    var status: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        transactionAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        transactionType = parcel.readString()
        merchantName = parcel.readString()
        merchantCategory = parcel.readString()
        merchantSubCategory = parcel.readString()
        customerUserId = parcel.readString()
        organizationId = parcel.readString()
        lmsCCAccountId = parcel.readString()
        lmsCCCardId = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
        transactionDate = parcel.readString()
        status = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeValue(transactionAmount)
        parcel.writeString(transactionType)
        parcel.writeString(merchantName)
        parcel.writeString(merchantCategory)
        parcel.writeString(merchantSubCategory)
        parcel.writeString(customerUserId)
        parcel.writeString(organizationId)
        parcel.writeString(lmsCCAccountId)
        parcel.writeString(lmsCCCardId)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
        parcel.writeString(transactionDate)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementTransactionInfo> {
        override fun createFromParcel(parcel: Parcel): StatementTransactionInfo {
            return StatementTransactionInfo(parcel)
        }

        override fun newArray(size: Int): Array<StatementTransactionInfo?> {
            return arrayOfNulls(size)
        }
    }
}