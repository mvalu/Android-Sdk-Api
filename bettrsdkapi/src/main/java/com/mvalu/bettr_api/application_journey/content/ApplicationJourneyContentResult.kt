package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class ApplicationJourneyContentResult {

    @field:Json(name = "dropdownDetail")
    var dropdownDetail: DropdownDetail? = null

    @field:Json(name = "placehoderList")
    var placeholderList: PlaceHolderList? = null

    class PlaceHolderList {
        @field:Json(name = "bureau_cc_consent")
        var bureauCCConsent: ConsentDetail? = null

        @field:Json(name = "location_cc_consent")
        var locationCCConsent: ConsentDetail? = null

        @field:Json(name = "job_detail_cc_consent")
        var jobDetailCCConsent: ConsentDetail? = null

        @field:Json(name = "payment_cc_consent")
        var paymentCCConsent: ConsentDetail? = null

        @field:Json(name = "account_info_cc_consent")
        var accountInfoCCConsent: ConsentDetail? = null

        @field:Json(name = "document_submission_cc_consent")
        var documentSubmissionInfoCCConsent: ConsentDetail? = null

        @field:Json(name = "reject_message_cc")
        var rejectMessageCCConsent: ConsentDetail? = null
    }

    class ConsentDetail {
        @field:Json(name = "description")
        var description: String? = null
    }

    class DropdownDetail {
        @field:Json(name = "city")
        var cities: List<DropdownItem>? = null

        @field:Json(name = "maritalStatus")
        var maritalStatuses: List<DropdownItem>? = null

        @field:Json(name = "companyType")
        var companyTypes: List<DropdownItem>? = null

        @field:Json(name = "noOfEmployeeInCompany")
        var noOfEmployeesInCompany: List<DropdownItem>? = null

        @field:Json(name = "roleInCompany")
        var rolesInCompany: List<DropdownItem>? = null

        @field:Json(name = "companyAreaOfBusiness")
        var companyAreasOfBusiness: List<DropdownItem>? = null

        @field:Json(name = "designation")
        var designations: List<DropdownItem>? = null

        @field:Json(name = "typeOfBusiness")
        var typesOfBusiness: List<DropdownItem>? = null

        @field:Json(name = "noOfEmployeeInBusiness")
        var noOfEmployeesInBusiness: List<DropdownItem>? = null

        @field:Json(name = "ownBusiness")
        var ownBusinesses: List<DropdownItem>? = null

        @field:Json(name = "gender")
        var genders: List<DropdownItem>? = null

        @field:Json(name = "areYouEmployed")
        var areYouEmployedOptions: List<DropdownItem>? = null

        @field:Json(name = "havingOfficeMail")
        var havingOfficeMailOptions: List<DropdownItem>? = null

        @field:Json(name = "residenceType")
        var residenceType: List<DropdownItem>? = null
    }
}