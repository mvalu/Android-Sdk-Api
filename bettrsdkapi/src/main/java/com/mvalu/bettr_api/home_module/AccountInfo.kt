package com.mvalu.bettr_api.home_module

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class AccountInfo() : Parcelable{
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "applicationId")
    var applicationId: String? = null

    @field:Json(name = "vendorId")
    var vendorId: String? = null

    @field:Json(name = "vendorIdentifier")
    var vendorIdentifier: String? = null

    @field:Json(name = "approvedAmount")
    var approvedAmount: Float? = null

    @field:Json(name = "balanceAmount")
    var balanceAmount: Float? = null

    @field:Json(name = "approvalDate")
    var approvalDate: String? = null

    @field:Json(name = "approvedTillDate")
    var approvedTillDate: String? = null

    @field:Json(name = "approvedROI")
    var approvedROI: Float? = null

    @field:Json(name = "isExpired")
    var isExpired: Boolean? = null

    @field:Json(name = "isClosedByUser")
    var isClosedByUser: Boolean? = null

    @field:Json(name = "isClosedByVendor")
    var isClosedByVendor: Boolean? = null

    @field:Json(name = "isClosedByAdmin")
    var isClosedByAdmin: Boolean? = null

    @field:Json(name = "isRejected")
    var isRejected: Boolean? = null

    @field:Json(name = "isActive")
    var isActive: Boolean? = null

    @field:Json(name = "closedReason")
    var closedReason: String? = null

    @field:Json(name = "rejectedReason")
    var rejectedReason: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "updatedAt")
    var updatedAt: String? = null

    @field:Json(name = "deletedAt")
    var deletedAt: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        customerUserId = parcel.readString()
        applicationId = parcel.readString()
        vendorId = parcel.readString()
        vendorIdentifier = parcel.readString()
        approvedAmount = parcel.readValue(Float::class.java.classLoader) as? Float
        balanceAmount = parcel.readValue(Float::class.java.classLoader) as? Float
        approvalDate = parcel.readString()
        approvedTillDate = parcel.readString()
        approvedROI = parcel.readValue(Float::class.java.classLoader) as? Float
        isExpired = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isClosedByUser = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isClosedByVendor = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isClosedByAdmin = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isRejected = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isActive = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        closedReason = parcel.readString()
        rejectedReason = parcel.readString()
        organizationId = parcel.readString()
        status = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        deletedAt = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(customerUserId)
        parcel.writeString(applicationId)
        parcel.writeString(vendorId)
        parcel.writeString(vendorIdentifier)
        parcel.writeValue(approvedAmount)
        parcel.writeValue(balanceAmount)
        parcel.writeString(approvalDate)
        parcel.writeString(approvedTillDate)
        parcel.writeValue(approvedROI)
        parcel.writeValue(isExpired)
        parcel.writeValue(isClosedByUser)
        parcel.writeValue(isClosedByVendor)
        parcel.writeValue(isClosedByAdmin)
        parcel.writeValue(isRejected)
        parcel.writeValue(isActive)
        parcel.writeString(closedReason)
        parcel.writeString(rejectedReason)
        parcel.writeString(organizationId)
        parcel.writeString(status)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
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