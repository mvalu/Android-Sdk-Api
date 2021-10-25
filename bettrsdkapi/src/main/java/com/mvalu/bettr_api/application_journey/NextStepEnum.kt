package com.mvalu.bettr_api.application_journey

enum class NextStepEnum(val value: String) {
    PAN("PAN"),
    DOB("DOB"),
    GENDER("GENDER"),
    PINCODE("PINCODE"),
    EMPLOYMENT_TYPE("EMPLOYMENT_TYPE"),
    DO_YOU_FILE_ITR("DO_YOU_FILE_ITR"),
    MONTHLY_INCOME("MONTHLY_INCOME"),
    DUMMY_CARD("DUMMY_CARD"),
    SMS_PERMISSION("SMS_PERMISSION"),
    SELFIE("SELFIE"),
    OKYC("OKYC"),
    ADDRESS_SELECT("ADDRESS_SELECT"),
    CURRENT_ADDRESS("CURRENT_ADDRESS"),
    PERMANENT_ADDRESS("PERMANENT_ADDRESS"),
    MAP("MAP"),
    TENTATIVE_OFFER("TENTATIVE_OFFER"),
    COMPANY_NAME("COMPANY_NAME"),
    UNDER_REVIEW("UNDER_REVIEW")
}