package com.mvalu.bettr_api.card_user

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

class UserDetail() : Parcelable {

    @field:Json(name = "id")
    var id: String? = null

    @field:Json(name = "mobileNumber")
    var mobileNumber: String? = null

    @field:Json(name = "mobileNumberCountryCodePrefix")
    var mobileNumberCountryCodePrefix: String? = null

    @field:Json(name = "isFirstTimeCustomer")
    var isFirstTime: Boolean? = false

    @field:Json(name = "firstName")
    var firstName: String? = null

    @field:Json(name = "middleName")
    var middleName: String? = null

    @field:Json(name = "lastName")
    var lastName: String? = null

    @field:Json(name = "status")
    var status: String? = null

    @field:Json(name = "city")
    var city: String? = null

    @field:Json(name = "state")
    var state: String? = null

    @field:Json(name = "pincode")
    var pincode: String? = null

    @field:Json(name = "addressLine1")
    var addressLine1: String? = null

    @field:Json(name = "addressLine2")
    var addressLine2: String? = null

    @field:Json(name = "gender")
    var gender: String? = null

    @field:Json(name = "dateOfBirth")
    var dateOfBirth: String? = null

    @Transient
    var dateOfBirthInput: String? = null

    @field:Json(name = "email")
    var email: String? = null

    @JvmSuppressWildcards
    @field:Json(name = "location")
    var location: Location? = null

    @field:Json(name = "aadharCard")
    var aadharCard: String? = null

    @field:Json(name = "aadharCardBack")
    var aadharCardBack: String? = null

    @field:Json(name = "photo")
    var photo: String? = null

    @field:Json(name = "bankAccountNumber")
    var bankAccountNumber: String? = null

    var bankAccountNumberEditable: String? = null

    @field:Json(name = "confirmBankAccountNumber")
    var confirmBankAccountNumber: String? = null

    var confirmBankAccountNumberEditable: String? = null

    @field:Json(name = "bankIfsc")
    var bankIfsc: String? = null

    var bankIfscEditable: String? = null

    @field:Json(name = "cancelledCheque")
    var cancelledCheque: String? = null

    @field:Json(name = "accountHolderName")
    var accountHolderName: String? = null

    var accountHolderNameEditable: String? = null

    @field:Json(name = "panVerified")
    var isPanVerified: Boolean? = false

    @field:Json(name = "aadharVerified")
    var isAadharVerified: Boolean? = false

    @field:Json(name = "isBankAccountVerified")
    var isBankAccountVerified: Boolean? = false


    //customer detail merge
    @field:Json(name = "residenceType")
    var residenceType: String? = null

    @field:Json(name = "residenceSince")
    var residenceSince: String? = null

//    @field:Json(name = "pincode")
//    var pinCode: String? = null//We won't use this

    @field:Json(name = "professionType")
    var professionType: String? = null

    @field:Json(name = "profession")
    var profession: String? = null

    @field:Json(name = "isExistingLoan")
    var isExistingLoan: Boolean? = null

    @field:Json(name = "isCreditCard")
    var isCreditCard: Boolean? = null

    @field:Json(name = "totalEMIPaid")
    var totalEMIPaid: String? = null

    @field:Json(name = "panNumber")
    var panNumber: String? = null

    @Transient
    var panNumberEditable: String? = null

    @field:Json(name = "aadharNumber")
    var aadharNumber: String? = null

    @Transient
    var aadharNumberEditable: String? = null

    @field:Json(name = "pan")
    var panImage: String? = null

    @field:Json(name = "takeHomeSalary")
    var takeHomeSalary: String? = null

    @field:Json(name = "modeOfSalary")
    var modeOfSalary: String? = null

    @field:Json(name = "bankName")
    var bankName: String? = null

    @field:Json(name = "employerName")
    var employerName: String? = null

    @field:Json(name = "designation")
    var designation: String? = null

    @field:Json(name = "timeSpentInCurrentJob")
    var timeSpentInCurrentJob: String? = null

    @field:Json(name = "workExperience")
    var workExperience: String? = null

    @field:Json(name = "fatherName")
    var fatherName: String? = null

    @field:Json(name = "motherName")
    var motherName: String? = null

    @field:Json(name = "maritalStatus")
    var maritalStatus: String? = null

    @field:Json(name = "officeEmail")
    var officeEmail: String? = null

    @field:Json(name = "officeAddress1")
    var officeAddress1: String? = null

    @field:Json(name = "officeAddress2")
    var officeAddress2: String? = null

    @field:Json(name = "officeAddressLandMark")
    var officeAddressLandMark: String? = null

    @field:Json(name = "officeAddressCity")
    var officeAddressCity: String? = null

    @field:Json(name = "officeAddressState")
    var officeAddressState: String? = null

    @field:Json(name = "officeAddressPincode")
    var officeAddressPinCode: String? = null

    @field:Json(name = "landMark")
    var landMark: String? = null

    @field:Json(name = "permanentAddress1")
    var permanentAddress1: String? = null

    @field:Json(name = "permanentAddress2")
    var permanentAddress2: String? = null

    @field:Json(name = "permanentAddressLandMark")
    var permanentAddressLandMark: String? = null

    @field:Json(name = "permanentAddressCity")
    var permanentAddressCity: String? = null

    @field:Json(name = "permanentAddressState")
    var permanentAddressState: String? = null

    @field:Json(name = "permanentAddressPincode")
    var permanentAddressPinCode: String? = null

    @field:Json(name = "isPermanentAddressSame")
    var isPermanentAddressSame: Boolean? = null

    @field:Json(name = "highestLevelOfEducation")
    var education: String? = null

    @field:Json(name = "lastEducationalInstitute")
    var lastEducationalInstitute: String? = null

    @field:Json(name = "bankStatement")
    var bankStatement: List<String>? = mutableListOf()

    @field:Json(name = "salarySlip")
    var salarySlip: List<String>? = mutableListOf()

    @field:Json(name = "idProofType")
    var idProofType: String? = null

    @field:Json(name = "idProof")
    var idProof: List<String>? = mutableListOf()

    @field:Json(name = "addressProofType")
    var addressProofType: String? = null

    @field:Json(name = "addressProof")
    var addressProof: List<String>? = mutableListOf()

    @field:Json(name = "emailAuthorizationToken")
    var emailAuthToken: String? = null

    @field:Json(name = "isEmailTokenValid")
    var isEmailTokenValid: Boolean? = null

    @field:Json(name = "panValidateId")
    var panValidateId: String? = null

    @field:Json(name = "aadharValidateId")
    var aadharValidateId: String? = null

    @field:Json(name = "photoValidateId")
    var photoValidateId: String? = null

    @field:Json(name = "aadharFaceCheckId")
    var aadharFaceCheckId: String? = null

    @field:Json(name = "panFaceCheckId")
    var panFaceCheckId: String? = null

    @field:Json(name = "whatsupConsent")
    var whatsAppConsent: Boolean? = false

    @field:Json(name = "preApproval")
    var preApproval: Boolean? = false

//    @field:Json(name = "preApprovalDetail")
//    var preApprovalDetail: PreApprovalDetail? = null

    @field:Json(name = "mapPinnedLatitude")
    var mapPinnedLatitude: Double? = null

    @field:Json(name = "mapPinnedLongitude")
    var mapPinnedLongitude: Double? = null

    @field:Json(name = "mapPinnedAddress")
    var mapPinnedAddress: String? = null

    /**
     * Other method
     */
    fun getAadharNumberDigits(): String? {
        return aadharNumberEditable?.replace("-", "")
    }


    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        mobileNumber = parcel.readString()
        mobileNumberCountryCodePrefix = parcel.readString()
        isFirstTime = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        firstName = parcel.readString()
        middleName = parcel.readString()
        lastName = parcel.readString()
        city = parcel.readString()
        state = parcel.readString()
        pincode = parcel.readString()
        addressLine1 = parcel.readString()
        addressLine2 = parcel.readString()
        gender = parcel.readString()
        dateOfBirth = parcel.readString()
        dateOfBirthInput = parcel.readString()
        email = parcel.readString()
        location = parcel.readParcelable(Location::class.java.classLoader)
        panNumber = parcel.readString()
        aadharNumber = parcel.readString()
        aadharCard = parcel.readString()
        photo = parcel.readString()
        aadharCardBack = parcel.readString()
        accountHolderName = parcel.readString()
        bankAccountNumber = parcel.readString()
        cancelledCheque = parcel.readString()
        bankIfsc = parcel.readString()
        confirmBankAccountNumber = parcel.readString()
        status = parcel.readString()
        isAadharVerified = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isPanVerified = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        isBankAccountVerified = parcel.readValue(Boolean::class.java.classLoader) as? Boolean

        panNumberEditable = parcel.readString()
        aadharNumberEditable = parcel.readString()
        accountHolderNameEditable = parcel.readString()
        bankAccountNumberEditable = parcel.readString()
        confirmBankAccountNumberEditable = parcel.readString()
        bankIfscEditable = parcel.readString()
        residenceSince = parcel.readString()
        residenceType = parcel.readString()
        professionType = parcel.readString()
        profession = parcel.readString()
        isExistingLoan = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        takeHomeSalary = parcel.readString()
        modeOfSalary = parcel.readString()
        bankName = parcel.readString()
        employerName = parcel.readString()
        timeSpentInCurrentJob = parcel.readString()
        workExperience = parcel.readString()
        fatherName = parcel.readString()
        motherName = parcel.readString()
        maritalStatus = parcel.readString()
        officeEmail = parcel.readString()
        officeAddress1 = parcel.readString()
        officeAddress2 = parcel.readString()
        officeAddressCity = parcel.readString()
        officeAddressLandMark = parcel.readString()
        officeAddressPinCode = parcel.readString()
        officeAddressState = parcel.readString()
        landMark = parcel.readString()
        permanentAddress1 = parcel.readString()
        permanentAddress2 = parcel.readString()
        permanentAddressCity = parcel.readString()
        permanentAddressLandMark = parcel.readString()
        permanentAddressPinCode = parcel.readString()
        permanentAddressState = parcel.readString()
        isPermanentAddressSame = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        education = parcel.readString()
        lastEducationalInstitute = parcel.readString()
        parcel.readStringList(bankStatement)
        parcel.readStringList(salarySlip)
        parcel.readStringList(idProof)
        parcel.readStringList(addressProof)
        emailAuthToken = parcel.readString()
        isEmailTokenValid = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        panValidateId = parcel.readString()
        aadharValidateId = parcel.readString()
        photoValidateId = parcel.readString()
        aadharFaceCheckId = parcel.readString()
        panFaceCheckId = parcel.readString()
        whatsAppConsent = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        preApproval = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
//        preApprovalDetail = parcel.readParcelable(PreApprovalDetail::class.java.classLoader)
        mapPinnedAddress = parcel.readString()
        mapPinnedLatitude = parcel.readValue(Double::class.java.classLoader) as? Double
        mapPinnedLongitude = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(mobileNumber)
        parcel.writeString(mobileNumberCountryCodePrefix)
        parcel.writeValue(isFirstTime)
        parcel.writeString(firstName)
        parcel.writeString(middleName)
        parcel.writeString(lastName)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(pincode)
        parcel.writeString(addressLine1)
        parcel.writeString(addressLine2)
        parcel.writeString(gender)
        parcel.writeString(dateOfBirth)
        parcel.writeString(dateOfBirthInput)
        parcel.writeString(email)
        parcel.writeParcelable(location, flags)
        parcel.writeString(panNumber)
        parcel.writeString(aadharNumber)
        parcel.writeString(aadharCard)
        parcel.writeString(photo)
        parcel.writeString(aadharCardBack)
        parcel.writeString(accountHolderName)
        parcel.writeString(bankAccountNumber)
        parcel.writeString(cancelledCheque)
        parcel.writeString(bankIfsc)
        parcel.writeString(confirmBankAccountNumber)
        parcel.writeString(status)
        parcel.writeValue(isAadharVerified)
        parcel.writeValue(isPanVerified)
        parcel.writeValue(isBankAccountVerified)

        parcel.writeString(panNumberEditable)
        parcel.writeString(aadharNumberEditable)
        parcel.writeString(accountHolderNameEditable)
        parcel.writeString(bankAccountNumberEditable)
        parcel.writeString(confirmBankAccountNumberEditable)
        parcel.writeString(bankIfscEditable)
        parcel.writeString(residenceSince)
        parcel.writeString(residenceType)
        parcel.writeString(professionType)
        parcel.writeString(profession)
        parcel.writeValue(isExistingLoan)
        parcel.writeString(takeHomeSalary)
        parcel.writeString(modeOfSalary)
        parcel.writeString(bankName)
        parcel.writeString(employerName)
        parcel.writeString(timeSpentInCurrentJob)
        parcel.writeString(workExperience)
        parcel.writeString(fatherName)
        parcel.writeString(motherName)
        parcel.writeString(maritalStatus)
        parcel.writeString(officeEmail)
        parcel.writeString(officeAddress1)
        parcel.writeString(officeAddress2)
        parcel.writeString(officeAddressCity)
        parcel.writeString(officeAddressLandMark)
        parcel.writeString(officeAddressPinCode)
        parcel.writeString(officeAddressState)
        parcel.writeString(landMark)
        parcel.writeString(permanentAddress1)
        parcel.writeString(permanentAddress2)
        parcel.writeString(permanentAddressCity)
        parcel.writeString(permanentAddressLandMark)
        parcel.writeString(permanentAddressPinCode)
        parcel.writeString(permanentAddressState)
        parcel.writeValue(isPermanentAddressSame)
        parcel.writeString(education)
        parcel.writeString(lastEducationalInstitute)
        parcel.writeList(bankStatement)
        parcel.writeList(salarySlip)
        parcel.writeList(idProof)
        parcel.writeList(addressProof)
        parcel.writeString(emailAuthToken)
        parcel.writeValue(isEmailTokenValid)
        parcel.writeString(panValidateId)
        parcel.writeString(aadharValidateId)
        parcel.writeString(photoValidateId)
        parcel.writeString(aadharFaceCheckId)
        parcel.writeString(panFaceCheckId)
        parcel.writeValue(whatsAppConsent)
        parcel.writeValue(preApproval)
//        parcel.writeParcelable(preApprovalDetail, flags)
        parcel.writeString(mapPinnedAddress)
        parcel.writeValue(mapPinnedLatitude)
        parcel.writeValue(mapPinnedLongitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDetail> {
        override fun createFromParcel(parcel: Parcel): UserDetail {
            return UserDetail(parcel)
        }

        override fun newArray(size: Int): Array<UserDetail?> {
            return arrayOfNulls(size)
        }
    }

    class Location() : Parcelable {
        @JvmSuppressWildcards
        @field:Json(name = "coordinates")
        var coordinates: List<Any>? = null

        constructor(parcel: Parcel) : this() {
            coordinates = parcel.readArrayList(Location::class.java.classLoader)
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeList(coordinates)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Location> {
            override fun createFromParcel(parcel: Parcel): Location {
                return Location(parcel)
            }

            override fun newArray(size: Int): Array<Location?> {
                return arrayOfNulls(size)
            }
        }
    }
}