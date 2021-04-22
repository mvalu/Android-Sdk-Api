package com.mvalu.bettr_api.account_statements

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class StatementSummaryItem() : Parcelable {

    @field:Json(name = "label")
    var label: String? = null

    @field:Json(name = "amount")
    var amount: Double? = null

    constructor(parcel: Parcel) : this() {
        label = parcel.readString()
        amount = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeValue(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementSummaryItem> {
        override fun createFromParcel(parcel: Parcel): StatementSummaryItem {
            return StatementSummaryItem(parcel)
        }

        override fun newArray(size: Int): Array<StatementSummaryItem?> {
            return arrayOfNulls(size)
        }
    }
}