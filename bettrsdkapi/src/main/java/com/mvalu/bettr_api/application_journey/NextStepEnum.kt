package com.mvalu.bettr_api.application_journey

enum class NextStepEnum(val value: String) {
    PAN("PAN"),
    RESIDENTIAL_DETAILS("RESIDENTIAL_DETAILS"),
    DOB("DOB"),
    GENDER("GENDER"),
    PINCODE("PINCODE"),
    EMPLOYMENT_TYPE("EMPLOYMENT_TYPE"),
    MONTHLY_INCOME("MONTHLY_INCOME"),
    DUMMY_CARD("DUMMY_CARD"),
    SELFIE("SELFIE"),
    OKYC("OKYC"),
    ADDRESS_SELECT("ADDRESS_SELECT")
}