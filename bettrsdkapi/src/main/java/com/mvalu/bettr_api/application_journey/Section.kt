package com.mvalu.bettr_api.application_journey

enum class Section(val value: String) {
    PERSONAL_DETAILS("personalDetail"),
    RESIDENTIAL_DETAILS("residentialAddress"),
    INCOME_DETAILS("incomeDetail"),
    INCOME_DOCUMENTS("incomeDocument"),
    KYC("kycDocument"),
    BANK_VERIFICATION("pennyDropBankAccount")
}