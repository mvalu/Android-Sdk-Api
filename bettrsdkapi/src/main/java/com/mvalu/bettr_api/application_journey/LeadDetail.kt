package com.mvalu.bettr_api.application_journey

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.mvalu.bettr_api.card_user.UserDetail
import com.squareup.moshi.Json
import java.util.*

class LeadDetail() : Parcelable {

    @Transient
    var reason: String? = null

    @field:Json(name = "productName")
    var productName: String? = null

    @field:Json(name = "createdAt")
    var createdAt: String? = null

    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "leadName")
    var leadName: String? = null

    @field:Json(name = "mobileNumber")
    var mobileNumber: String? = null

    @field:Json(name = "loanAmount")
    var loanAmount: String? = null

    @field:Json(name = "tenure")
    var tenure: String? = null //for Pre-market place

    @field:Json(name = "loanPurpose")
    var purpose: String? = null

    @field:Json(name = "customerUserId")
    var customerUserId: String? = null

    @field:Json(name = "channelTenantId")
    var channelTenantId: String? = null

    @field:Json(name = "productType")
    var productType: String? = null

    @field:Json(name = "channelUserId")
    var channelUserId: String? = null

    @field:Json(name = "pincode")
    var pinCode: String? = null//for Pre-market place. Lead's pinCode

    @field:Json(name = "checkerId")
    var checkerId: String? = null

//    @field:Json(name = "earnings")
//    var earnings: List<EarningsItem>? = null

    @field:Json(name = "applicationDetail")
    var application: ApplicationDetail? = null

    @field:Json(name = "userDetail")
    var userDetail: UserDetail? = UserDetail()

    @field:Json(name = "timelines")
    var timeLines: Map<String, String?>? = null

//    @field:Json(name = "isActive")
//    var isActive: Boolean? = null

    @field:Json(name = "leadRejected")
    var leadRejected: Boolean? = false

    /*@field:Json(name = "confirmLocationSubmission")
    var confirmLocationSubmission: Boolean? = false

    @field:Json(name = "personalDetailSubmission")
    var personalDetailSubmission: Boolean? = false

    @field:Json(name = "bureauConsentTaken")
    var bureauConsentTaken: Boolean? = false

    @field:Json(name = "residentialAddressSubmission")
    var residentialAddressSubmission: Boolean? = false

    @field:Json(name = "pincodeCheckSubmission")
    var pincodeCheckSubmission: Boolean? = false

    @field:Json(name = "incomeDetailSubmission")
    var incomeDetailSubmission: Boolean? = false

    @field:Json(name = "kycDocumentSubmission")
    var kycDocumentSubmission: Boolean? = false

    @field:Json(name = "pennyDropBankAccountSubmission")
    var pennyDropBankAccountSubmission: Boolean? = false

    @field:Json(name = "bankAccountVerificationCompleted")
    var bankAccountVerificationCompleted: Boolean? = false*/

    @field:Json(name = "bureauAnswer")
    var bureauAnswer: Boolean? = false

    @field:Json(name = "bureauKnowledge")
    var bureauKnowledge: Boolean? = false

    @field:Json(name = "bureauVerified")
    var bureauVerified: Boolean? = false

    @field:Json(name = "leadRejectedReason")
    var leadRejectedReason: String? = null

    @field:Json(name = "secctionDetail")
    var sectionDetail: SectionDetail? = null

    constructor(parcel: Parcel) : this() {
        productName = parcel.readString()
        createdAt = parcel.readString()
        id = parcel.readString()
        status = parcel.readString()
        leadName = parcel.readString()
        mobileNumber = parcel.readString()
        loanAmount = parcel.readString()
        tenure = parcel.readString()
        purpose = parcel.readString()
        customerUserId = parcel.readString()
        channelTenantId = parcel.readString()
        productType = parcel.readString()
        channelUserId = parcel.readString()
        pinCode = parcel.readString()
        checkerId = parcel.readString()
//        earnings = parcel.createTypedArrayList(EarningsItem)
        application = parcel.readParcelable(ApplicationDetail::class.java.classLoader)
        userDetail = parcel.readParcelable(UserDetail::class.java.classLoader)
        timeLines = parcel.readValue(Map::class.java.classLoader) as? Map<String, String?>
//        parcel.readMap(timeLines, String.javaClass.classLoader)
//        isActive = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        leadRejected = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        bureauAnswer = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        bureauKnowledge = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        bureauVerified = parcel.readValue(Boolean::class.java.classLoader) as? Boolean

        leadRejectedReason = parcel.readString()
        sectionDetail = parcel.readParcelable(SectionDetail::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productName)
        parcel.writeString(createdAt)
        parcel.writeString(id)
        parcel.writeString(status)
        parcel.writeString(leadName)
        parcel.writeString(mobileNumber)
        parcel.writeString(loanAmount)
        parcel.writeString(tenure)
        parcel.writeString(purpose)
        parcel.writeString(customerUserId)
        parcel.writeString(channelTenantId)
        parcel.writeString(productType)
        parcel.writeString(channelUserId)
        parcel.writeString(pinCode)
        parcel.writeString(checkerId)
//        parcel.writeTypedList(earnings)
        parcel.writeParcelable(application, flags)
        parcel.writeParcelable(userDetail, flags)
//        parcel.writeBundle(writeMapToBundle(timeLines))
        parcel.writeValue(timeLines)
//        parcel.writeMap(timeLines)
//        parcel.writeValue(isActive)
        parcel.writeValue(leadRejected)
        parcel.writeValue(bureauAnswer)
        parcel.writeValue(bureauKnowledge)
        parcel.writeValue(bureauVerified)
        parcel.writeString(leadRejectedReason)
        parcel.writeParcelable(sectionDetail, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LeadDetail> {
        override fun createFromParcel(parcel: Parcel): LeadDetail {
            return LeadDetail(parcel)
        }

        override fun newArray(size: Int): Array<LeadDetail?> {
            return arrayOfNulls(size)
        }
    }


    /**
     * Other methods
     */
    fun getCustomerFirstNameWithFallBack(): String? {
        return if (userDetail?.firstName.isNullOrBlank())
            leadName
        else
            userDetail?.firstName
    }

    fun getFirstCharOfLastNameUpperCase(): Char? {
        return if (userDetail?.lastName.isNullOrBlank()) {
            null
        } else
            userDetail?.lastName!!.toUpperCase(Locale.ENGLISH)[0]
    }


    private fun writeMapToBundle(map: Map<String, String?>?): Bundle? {
        if (map.isNullOrEmpty())
            return null

        val bundle = Bundle()
        for ((K, V) in map!!) {
            bundle.putString(K, V)
        }
        return bundle
    }

    private fun readMapFromBundle(bundle: Bundle?): Map<String, String?>? {
        if (bundle == null || bundle.isEmpty)
            return null

        val map: HashMap<String, String?> = HashMap()
        for (key in bundle.keySet()) {
            map[key] = bundle.getString(key)
        }
        return map
    }

    /**
     * ----------
     * Other code
     * ----------
     */
//    fun getProductNameToDisplay(): String? {
////        return if (productName.isNullOrBlank()) {
////            getProductNameToDisplay(productType)
////        } else
//            productName
//    }

//    fun getStatusToDisplay(): String? {
//        return getStatusToDisplay(true)
//    }

//    fun getStatusToDisplay(isFormatted: Boolean): String? {
//        return if (application != null && !application?.status.isNullOrBlank()) {
//            if (isFormatted)
//                getLeadStatusToDisplayForLeadsList(application?.status!!).replace("_", " ")
//            else
//                getLeadStatusToDisplayForLeadsList(application?.status!!)
//        } else if (!status.isNullOrBlank()) {
//            if (isFormatted)
//                getLeadStatusToDisplayForLeadsList(status!!).replace("_", " ")
//            else
//                getLeadStatusToDisplayForLeadsList(status!!)
//        } else {
//            if (isFormatted)
//                status?.replace("_", " ")
//            else
//                status
//        }
//    }

//    fun getLeadStatusTagIcon(): Int {
//        return getLeadStatusTagIcon(status)
//    }

    /**
     * if user detail object has user's name then return it, else return lead name.
     */
    fun getApplicantName(): String? {
        return if (userDetail != null && !userDetail!!.firstName.isNullOrBlank()) {
            "${userDetail!!.firstName} ${userDetail!!.middleName ?: ""} ${userDetail!!.lastName
                ?: ""}"
        } else
            leadName
    }

    /**
     * Returns date for respective lead status (backend status).
     */
    fun getDateForStatus(status: String): String? {
        return if (!timeLines.isNullOrEmpty()) {
            if (timeLines!!.containsKey(status)) {
                timeLines!!.getValue(status)
            } else null
        } else null
    }

    /**
     * Returns total earnings amount for this lead
     */
    fun getTotalEarningsForLead(): Int {
        var totalAmount = 0
//        if (!earnings.isNullOrEmpty()) {
//            for (earning in earnings!!) {
//                totalAmount += earning.amount ?: 0
//            }
//        }

        return totalAmount
    }
}