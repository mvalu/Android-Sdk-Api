package com.mvalu.bettr_api.account_statements

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class StatementInfo() : Parcelable {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "paymentStatus")
    var paymentStatus: String? = null

    @field:Json(name = "startDate")
    var startDate: String? = null

    @field:Json(name = "endDate")
    var endDate: String? = null

    @field:Json(name = "startingBalance")
    var startingBalance: Double? = null

    @field:Json(name = "endingBalance")
    var endingBalance: Double? = null

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

    @field:Json(name = "transactionCount")
    var transactionCount: Int? = 0

    @field:Json(name = "statementTransactionCount")
    var statementTransactionCount: Int? = 0

//    @field:Json(name = "transactionAmount")
//    var transactionAmount: Double? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    @field:Json(name = "minimumAmount")
    var minimumAmount: Double? = null

    @field:Json(name = "paymentAmount")
    var paymentAmount: Double? = null

    @field:Json(name = "pdfUrl")
    var pdfUrl: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        paymentStatus = parcel.readString()
        startDate = parcel.readString()
        endDate = parcel.readString()
        startingBalance = parcel.readValue(Double::class.java.classLoader) as? Double
        endingBalance = parcel.readValue(Double::class.java.classLoader) as? Double
        customerUserId = parcel.readString()
        organizationId = parcel.readString()
        lmsCCAccountId = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
        transactionCount = parcel.readValue(Double::class.java.classLoader) as? Int
        statementTransactionCount = parcel.readValue(Double::class.java.classLoader) as? Int
//        transactionAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
        minimumAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        paymentAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        pdfUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(paymentStatus)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeValue(startingBalance)
        parcel.writeValue(endingBalance)
        parcel.writeString(customerUserId)
        parcel.writeString(organizationId)
        parcel.writeString(lmsCCAccountId)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
        parcel.writeValue(transactionCount)
        parcel.writeValue(statementTransactionCount)
//        parcel.writeValue(transactionAmount)
        parcel.writeValue(amount)
        parcel.writeValue(minimumAmount)
        parcel.writeValue(paymentAmount)
        parcel.writeString(pdfUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementInfo> {
        override fun createFromParcel(parcel: Parcel): StatementInfo {
            return StatementInfo(parcel)
        }

        override fun newArray(size: Int): Array<StatementInfo?> {
            return arrayOfNulls(size)
        }
    }
}