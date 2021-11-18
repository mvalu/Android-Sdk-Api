package com.mvalu.bettr_api.application_journey

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class TargetScreenResponseModel() : Parcelable {
    @field:Json(name = "targetscreen")
    var targetscreen: String? = null

    constructor(parcel: Parcel) : this() {
        targetscreen = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        targetscreen = parcel.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TargetScreenResponseModel> {
        override fun createFromParcel(parcel: Parcel): TargetScreenResponseModel {
            return TargetScreenResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<TargetScreenResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}