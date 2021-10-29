package com.mvalu.bettr_api.home_module

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class CardInfo() : Parcelable {
    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "cardNumber")
    var cardNumber: String? = null

    @field:Json(name = "cardIdentifier")
    var cardIdentifier: String? = null

    @field:Json(name = "organizationId")
    var organizationId: String? = null

    @field:Json(name = "LmsCCAccountId")
    var lmsCCAccountId: String? = null

    @field:Json(name = "cardNetwork")
    var cardNetwork: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "cvv")
    var cvv: String? = null

    @field:Json(name = "expiryDate")
    var expiryDate: String? = null

    @field:Json(name = "activationDate")
    var activationDate: String? = null

    @field:Json(name = "closedDate")
    var closedDate: String? = null

    @field:Json(name = "type")
    var type: String? = null


    @field:Json(name = "cardUserName")
    var cardUserName: String? = null



    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        customerUserId = parcel.readString()
        cardNumber = parcel.readString()
        cardIdentifier = parcel.readString()
        organizationId = parcel.readString()
        lmsCCAccountId = parcel.readString()
        cardNetwork = parcel.readString()
        status = parcel.readString()
        cvv = parcel.readString()
        expiryDate = parcel.readString()
        activationDate = parcel.readString()
        closedDate = parcel.readString()
        type = parcel.readString()
        cardUserName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(customerUserId)
        parcel.writeString(cardNumber)
        parcel.writeString(cardIdentifier)
        parcel.writeString(organizationId)
        parcel.writeString(lmsCCAccountId)
        parcel.writeString(cardNetwork)
        parcel.writeString(status)
        parcel.writeString(cvv)
        parcel.writeString(expiryDate)
        parcel.writeString(activationDate)
        parcel.writeString(closedDate)
        parcel.writeString(type)
        parcel.writeString(cardUserName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardInfo> {
        override fun createFromParcel(parcel: Parcel): CardInfo {
            return CardInfo(parcel)
        }

        override fun newArray(size: Int): Array<CardInfo?> {
            return arrayOfNulls(size)
        }
    }
}