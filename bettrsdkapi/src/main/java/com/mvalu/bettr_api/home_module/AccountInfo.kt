package com.mvalu.bettr_api.home_module

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class AccountInfo() : Parcelable {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "applicationId")
    var applicationId: String? = null

    @field:Json(name = "vendorId")
    var vendorId: String? = null

    @field:Json(name = "vendorIdentifier")
    var vendorIdentifier: String? = null

    @field:Json(name = "approvedAmount")
    var approvedAmount: Double? = null

    @field:Json(name = "usableAmount")
    var usableAmount: Double? = null

    @field:Json(name = "balanceAmount")
    var balanceAmount: Double? = null

    @field:Json(name = "approvalDate")
    var approvalDate: String? = null

    @field:Json(name = "approvedTillDate")
    var approvedTillDate: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null

    @field:Json(name = "cardList")
    var cardInfo: CardInfo? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        organizationId = parcel.readString()
        customerUserId = parcel.readString()
        applicationId = parcel.readString()
        vendorId = parcel.readString()
        vendorIdentifier = parcel.readString()
        approvedAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        usableAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        balanceAmount = parcel.readValue(Double::class.java.classLoader) as? Double
        approvalDate = parcel.readString()
        approvedTillDate = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
        cardInfo = parcel.readParcelable(CardInfo::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(organizationId)
        parcel.writeString(customerUserId)
        parcel.writeString(applicationId)
        parcel.writeString(vendorId)
        parcel.writeString(vendorIdentifier)
        parcel.writeValue(approvedAmount)
        parcel.writeValue(usableAmount)
        parcel.writeValue(balanceAmount)
        parcel.writeString(approvalDate)
        parcel.writeString(approvedTillDate)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
        parcel.writeParcelable(cardInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccountInfo> {
        override fun createFromParcel(parcel: Parcel): AccountInfo {
            return AccountInfo(parcel)
        }

        override fun newArray(size: Int): Array<AccountInfo?> {
            return arrayOfNulls(size)
        }
    }


}