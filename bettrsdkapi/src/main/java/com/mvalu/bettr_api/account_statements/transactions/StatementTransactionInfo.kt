package com.mvalu.bettr_api.account_statements.transactions

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class StatementTransactionInfo() : Parcelable {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "type")
    var type: String? = null

    @field:Json(name = "LmsCCTransactionId")
    var lmsCCTransactionId: String? = null

    @field:Json(name = "LmsCCTransactionEmiId")
    var lmsCCTransactionEmiId: String? = null

    @field:Json(name = "transactionType")
    var transactionType: String? = null

    @field:Json(name = "amount")
    var amount: Float? = null

    @field:Json(name = "merchantName")
    var merchantName: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "LmsCCAccountId")
    var lmsCCAccountId: String? = null

    @field:Json(name = "LmsCCCardId")
    var lmsCCCardId: String? = null

    @field:Json(name = "LmsCCStatementId")
    var lmsCCStatementId: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        type = parcel.readString()
        lmsCCTransactionId = parcel.readString()
        lmsCCTransactionEmiId = parcel.readString()
        transactionType = parcel.readString()
        amount = parcel.readValue(Float::class.java.classLoader) as? Float
        merchantName = parcel.readString()
        customerUserId = parcel.readString()
        organizationId = parcel.readString()
        lmsCCAccountId = parcel.readString()
        lmsCCCardId = parcel.readString()
        lmsCCStatementId = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(type)
        parcel.writeString(lmsCCTransactionId)
        parcel.writeString(lmsCCTransactionEmiId)
        parcel.writeString(transactionType)
        parcel.writeValue(amount)
        parcel.writeString(merchantName)
        parcel.writeString(customerUserId)
        parcel.writeString(organizationId)
        parcel.writeString(lmsCCAccountId)
        parcel.writeString(lmsCCCardId)
        parcel.writeString(lmsCCStatementId)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementTransactionInfo> {
        override fun createFromParcel(parcel: Parcel): StatementTransactionInfo {
            return StatementTransactionInfo(
                parcel
            )
        }

        override fun newArray(size: Int): Array<StatementTransactionInfo?> {
            return arrayOfNulls(size)
        }
    }
}