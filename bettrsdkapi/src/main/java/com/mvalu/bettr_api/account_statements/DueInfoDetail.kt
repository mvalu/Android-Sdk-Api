package com.mvalu.bettr_api.account_statements

import android.os.Parcel
import android.os.Parcelable
import com.mvalu.bettr_api.home_module.DueInfo
import com.mvalu.bettr_api.payment.summary.FineInfo
import com.mvalu.bettr_api.payment.summary.NewSpendsInfo
import com.mvalu.bettr_api.payment.summary.StatementBalanceInfo
import com.squareup.moshi.Json

class DueInfoDetail() : Parcelable {
    @field:Json(name = "dueInfo")
    var dueInfo: DueInfo? = null

    @field:Json(name = "version")
    var version: String? = null

    @field:Json(name = "fineInfo")
    var fineInfo: FineInfo? = null

    @field:Json(name = "newSpendsInfo")
    var newSpendsInfo: NewSpendsInfo? = null

    @field:Json(name = "statementBalanceInfo")
    var statementBalanceInfo: StatementBalanceInfo? = null

    constructor(parcel: Parcel) : this() {
        dueInfo = parcel.readParcelable(DueInfo::class.java.classLoader)
        version = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(dueInfo, flags)
        parcel.writeString(version)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DueInfoDetail> {
        override fun createFromParcel(parcel: Parcel): DueInfoDetail {
            return DueInfoDetail(parcel)
        }

        override fun newArray(size: Int): Array<DueInfoDetail?> {
            return arrayOfNulls(size)
        }
    }
}
