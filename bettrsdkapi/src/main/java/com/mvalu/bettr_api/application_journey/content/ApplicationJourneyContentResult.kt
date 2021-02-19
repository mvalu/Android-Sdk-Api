package com.mvalu.bettr_api.application_journey.content

import com.squareup.moshi.Json

class ApplicationJourneyContentResult {

    @field:Json(name = "dropdownDetail")
    var dropdownDetail: DropdownDetail? = null

    @field:Json(name = "placehoderList")
    var placeholderList: PlaceHolderList? = null

    class PlaceHolderList {
        /** These will be removed later as not in use */
        @field:Json(name = "bureau_cc_consent")
        var bureauCCConsent: List<ConsentDetail>? = null

        @field:Json(name = "location_cc_consent")
        var locationCCConsent: List<ConsentDetail>? = null

        @field:Json(name = "job_detail_cc_consent")
        var jobDetailCCConsent: List<ConsentDetail>? = null

        @field:Json(name = "payment_cc_consent")
        var paymentCCConsent: List<ConsentDetail>? = null

        @field:Json(name = "account_info_cc_consent")
        var accountInfoCCConsent: List<ConsentDetail>? = null

        @field:Json(name = "document_submission_cc_consent")
        var documentSubmissionInfoCCConsent: List<ConsentDetail>? = null

        @field:Json(name = "reject_message_cc")
        var rejectMessageCCConsent: List<ConsentDetail>? = null

        /** Following will be in use */

        @field:Json(name = "pincode_section_end_cc")
        var pincodeSectionEndCC: List<ConsentDetail>? = null

        @field:Json(name = "personal_details_start_cc")
        var personalDetailsStartCC: List<ConsentDetail>? = null

        @field:Json(name = "personal_details_end_cc")
        var personalDetailsEndCC: List<ConsentDetail>? = null

        @field:Json(name = "residential_details_start_cc")
        var residentialDetailsStartCC: List<ConsentDetail>? = null

        @field:Json(name = "income_details_start_cc")
        var incomeDetailsStartCC: List<ConsentDetail>? = null

        @field:Json(name = "income_details_end_cc")
        var incomeDetailsEndCC: List<ConsentDetail>? = null

        @field:Json(name = "business_docs_submit_start_cc")
        var businessDocsSubmitStartCC: List<ConsentDetail>? = null

        @field:Json(name = "business_docs_submit_end_cc")
        var businessDocsSubmitEndCC: List<ConsentDetail>? = null

        @field:Json(name = "kyc_docs_start_cc")
        var kycDocsStartCC: List<ConsentDetail>? = null

        @field:Json(name = "kyc_docs_end_cc")
        var kycDocsEndCC: List<ConsentDetail>? = null

        @field:Json(name = "bank_details_start_cc")
        var bankDetailsStartCC: List<ConsentDetail>? = null

        @field:Json(name = "bank_details_end_cc")
        var bankDetailsEndCC: List<ConsentDetail>? = null

        @field:Json(name = "nach_end_cc")
        var nachEndCC: List<ConsentDetail>? = null

        @field:Json(name = "aadhar_ekyc_start_cc")
        var aadharEkycStartCC: List<ConsentDetail>? = null

        @field:Json(name = "application_submitted_cc")
        var applicationSubmittedCC: List<ConsentDetail>? = null

        @field:Json(name = "lead_success_cc")
        var leadSuccessCC: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc")
        var leadRejectCC: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_DEDUPE_REJECTED")
        var leadRejectCCDedupe: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_MARKET_LIST_REJECTED")
        var leadRejectCCMarketList: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_PINCODE_REJECTED")
        var leadRejectCCPincode: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_SERVICE_AREA_REJECTED")
        var leadRejectCCServiceArea: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_AGE_REJECTED")
        var leadRejectCCAge: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_SERVICE_AREA_AND_AGE_REJECTED")
        var leadRejectCCServiceAreaAndAge: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_CIBIL_REJECTED")
        var leadRejectCCCibil: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_CIBIL_FAILED")
        var leadRejectCCCibilFailed: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_BUREAU_REJECTED")
        var leadRejectCCBureau: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_QDE_REJECTED")
        var leadRejectCCQde: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_LOCATION_REJECTED")
        var leadRejectCCLocation: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_ACCOUNT_VERIFICATION_FAILED")
        var leadRejectCCAccountVerification: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_RAZORPAY_ENACH_FAILED")
        var leadRejectCCEnachFailed: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_PAN_VERIFICATION_FAILED")
        var leadRejectCCPanVarificationFailed: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_REASON_UNKNOWN")
        var leadRejectCCReasonUnknown: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_EMAIL_STATEMENT_FETCH_FAILED")
        var leadRejectCCEmailStmtFetchFailed: List<ConsentDetail>? = null

        @field:Json(name = "lead_reject_cc_RISK_POLICY_EXPIRED")
        var leadRejectCCRiskPolicyExpired: List<ConsentDetail>? = null

    }

    class ConsentDetail {
        @field:Json(name = "title")
        var title: String? = null

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