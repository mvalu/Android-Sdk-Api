package com.mvalu.bettr_api.application_journey.bureau

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class BureauStatusResult() : Parcelable {
    @field:Json(name = "applicationId")
    var applicationId: String? = null

    @field:Json(name = "bureauApplicationParserId")
    var bureauApplicationParserId: String? = null

    @field:Json(name = "status")
    var status: BureauStatus? = null

    @field:Json(name = "scoreLessThan300")
    var scoreLessThan300: Boolean = false

    constructor(parcel: Parcel) : this() {
        applicationId = parcel.readString()
        bureauApplicationParserId = parcel.readString()
        scoreLessThan300 = parcel.readByte() != 0.toByte()
    }

    enum class BureauStatus {
        @Json(name = "SUCCESS")
        SUCCESS,
        @Json(name = "QUESTION")
        QUESTION,
        @Json(name = "FAILURE")
        FAILURE,
        @Json(name = "NOHIT")
        NOHIT,
        @Json(name = "BUREAU_DOWN")
        BUREAU_DOWN
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(applicationId)
        parcel.writeString(bureauApplicationParserId)
        parcel.writeByte(if (scoreLessThan300) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BureauStatusResult> {
        override fun createFromParcel(parcel: Parcel): BureauStatusResult {
            return BureauStatusResult(parcel)
        }

        override fun newArray(size: Int): Array<BureauStatusResult?> {
            return arrayOfNulls(size)
        }
    }
}