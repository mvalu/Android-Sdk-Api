package com.mvalu.bettr_api.application_journey

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class SectionDetail() : Parcelable {

    @field:Json(name = "nextSection")
    var nextSection: String? = null //Section.PERSONAL_DETAILS

    constructor(parcel: Parcel) : this() {
        nextSection = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nextSection)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SectionDetail> {
        override fun createFromParcel(parcel: Parcel): SectionDetail {
            return SectionDetail(parcel)
        }

        override fun newArray(size: Int): Array<SectionDetail?> {
            return arrayOfNulls(size)
        }
    }

}