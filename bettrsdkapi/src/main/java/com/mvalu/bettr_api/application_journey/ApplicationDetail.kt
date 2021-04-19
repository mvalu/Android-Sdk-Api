package com.mvalu.bettr_api.application_journey

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class ApplicationDetail() : Parcelable {

    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "otpNumber")
    var otpNumber: String? = null

    @field:Json(name = "vendorApplicationId")
    var vendorApplicationId: String? = null//We will not use this on Android

    @field:Json(name = "productId")
    var productId: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "interest")
    var interestRate: Float? = null

    @field:Json(name = "loanAmount")
    var loanAmount: Int? = null

    @field:Json(name = "emi")
    var emi: Int? = null

    @field:Json(name = "tenure")
    var tenure: String? = null

    @field:Json(name = "brandLogo")
    var brandLogo: String? = null

    @field:Json(name = "brandColor")
    var brandColor: String? = null

    @field:Json(name = "vendorDisplayName")
    var vendorDisplayName: String? = null

    @field:Json(name = "LeadId")
    var leadId: String? = null

    @field:Json(name = "isQDEFailed")
    var isQDEFailed: Boolean? = null

    @field:Json(name = "isEmailFetchSuccess")
    var isEmailFetchSuccess: Boolean? = null

    @field:Json(name = "isQDEReady")
    var isQDEReady: Boolean? = true

    @field:Json(name = "isDuplicateFailed")
    var isDuplicateFailed: Boolean? = null

    @field:Json(name = "userApplicationSubmitted")
    var userApplicationSubmitted: Boolean? = null

    @field:Json(name = "leadRejected")
    var leadRejected: Boolean? = false

    @field:Json(name = "officeMailIdVerified")
    var officeMailIdVerified: Boolean? = false

    @field:Json(name = "bankAccountNumber")
    var bankAccountNumber: String? = null

    @Transient
    var bankAccountNumberEditable: String? = null

    @field:Json(name = "bankIfsc")
    var bankIfsc: String? = null

    @Transient
    var bankIfscEditable: String? = null

    @field:Json(name = "accountHolderName")
    var accountHolderName: String? = null

    @field:Json(name = "isBankAccountVerified")
    var isBankAccountVerified: Boolean? = false

    @field:Json(name = "bankName")
    var bankName: String? = null

    @field:Json(name = "nachRazorpayTokenId")
    var nachRazorpayTokenId: String? = null

    @field:Json(name = "leadRejectedReason")
    var leadRejectedReason: String? = null

    @field:Json(name = "isRazorpayFailed")
    var isRazorpayFailed: Boolean? = false

    @field:Json(name = "showErrorAndroid")
    var showErrorAndroid: Boolean? = false

    @field:Json(name = "isEnachSkip")
    var isEnachSkip: Boolean? = false

    @field:Json(name = "showErrorAndroidMessage")
    var showErrorAndroidMessage: ErrorMessage? = null

    @field:Json(name = "personalDetailSubmission")
    var personalDetailSubmission: String? = null

    @field:Json(name = "residentialAddressSubmission")
    var residentialAddressSubmission: String? = null

    @field:Json(name = "incomeDetailSubmission")
    var incomeDetailSubmission: String? = null

    @field:Json(name = "incomeDocumentSubmission")
    var incomeDocumentSubmission: String? = null

    @field:Json(name = "kycDocumentSubmission")
    var kycDocumentSubmission: String? = null

    @field:Json(name = "pennyDropBankAccountSubmission")
    var pennyDropBankAccountSubmission: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        otpNumber = parcel.readString()
        vendorApplicationId = parcel.readString()
        productId = parcel.readString()
        status = parcel.readString()
        interestRate = parcel.readValue(Float::class.java.classLoader) as? Float
        loanAmount = parcel.readValue(Int::class.java.classLoader) as? Int
        emi = parcel.readValue(Int::class.java.classLoader) as? Int
        tenure = parcel.readString()
        brandLogo = parcel.readString()
        brandColor = parcel.readString()
        vendorDisplayName = parcel.readString()
        leadId = parcel.readString()
        isQDEFailed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isEmailFetchSuccess = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isQDEReady = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isDuplicateFailed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        userApplicationSubmitted = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        leadRejected = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        officeMailIdVerified = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        bankAccountNumber = parcel.readString()
        bankIfsc = parcel.readString()
        accountHolderName = parcel.readString()
        isBankAccountVerified = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        bankName = parcel.readString()
        nachRazorpayTokenId = parcel.readString()
        leadRejectedReason = parcel.readString()
        isRazorpayFailed = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        showErrorAndroid = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isEnachSkip = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        showErrorAndroidMessage = parcel.readParcelable(ErrorMessage::class.java.classLoader)
        personalDetailSubmission = parcel.readString()
        residentialAddressSubmission = parcel.readString()
        incomeDetailSubmission = parcel.readString()
        incomeDocumentSubmission = parcel.readString()
        kycDocumentSubmission = parcel.readString()
        pennyDropBankAccountSubmission = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(otpNumber)
        parcel.writeString(vendorApplicationId)
        parcel.writeString(productId)
        parcel.writeString(status)
        parcel.writeValue(interestRate)
        parcel.writeValue(loanAmount)
        parcel.writeValue(emi)
        parcel.writeString(tenure)
        parcel.writeString(brandLogo)
        parcel.writeString(brandColor)
        parcel.writeString(vendorDisplayName)
        parcel.writeString(leadId)
        parcel.writeValue(isQDEFailed)
        parcel.writeValue(isEmailFetchSuccess)
        parcel.writeValue(isQDEReady)
        parcel.writeValue(isDuplicateFailed)
        parcel.writeValue(userApplicationSubmitted)
        parcel.writeValue(leadRejected)
        parcel.writeValue(officeMailIdVerified)
        parcel.writeString(bankAccountNumber)
        parcel.writeString(bankIfsc)
        parcel.writeString(accountHolderName)
        parcel.writeValue(isBankAccountVerified)
        parcel.writeString(bankName)
        parcel.writeString(nachRazorpayTokenId)
        parcel.writeString(leadRejectedReason)
        parcel.writeValue(isRazorpayFailed)
        parcel.writeValue(showErrorAndroid)
        parcel.writeValue(isEnachSkip)
        parcel.writeParcelable(showErrorAndroidMessage, flags)
        parcel.writeString(personalDetailSubmission)
        parcel.writeString(residentialAddressSubmission)
        parcel.writeString(incomeDetailSubmission)
        parcel.writeString(incomeDocumentSubmission)
        parcel.writeString(kycDocumentSubmission)
        parcel.writeString(pennyDropBankAccountSubmission)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApplicationDetail> {
        override fun createFromParcel(parcel: Parcel): ApplicationDetail {
            return ApplicationDetail(parcel)
        }

        override fun newArray(size: Int): Array<ApplicationDetail?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * Error message class in case of lead rejection or any other error.
     * This will open bottom sheet
     */
    class ErrorMessage() : Parcelable{

        @field:Json(name = "title")
        var title: String? = null

        @field:Json(name = "body")
        var body: String? = null

        constructor(parcel: Parcel) : this() {
            title = parcel.readString()
            body = parcel.readString()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(title)
            parcel.writeString(body)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ErrorMessage> {
            override fun createFromParcel(parcel: Parcel): ErrorMessage {
                return ErrorMessage(parcel)
            }

            override fun newArray(size: Int): Array<ErrorMessage?> {
                return arrayOfNulls(size)
            }
        }
    }
}