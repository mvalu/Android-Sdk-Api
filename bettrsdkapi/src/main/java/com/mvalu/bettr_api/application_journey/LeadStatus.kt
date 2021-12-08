package com.mvalu.bettr_api.application_journey

enum class LeadStatus(val value: String) {
    INCOMPLETE("INCOMPLETE"),
    IN_PROGRESS("IN_PROGRESS"),
    PENDING_VERIFICATION("PENDING_VERIFICATION"),
    SUBMITTED("SUBMITTED"),
    APPROVED("APPROVED"),
    COMPLETED_ALL("COMPLETED"),
    REJECTED("REJECTED"),
    DISBURSED("DISBURSED"),
    MOBILE_VERIFIED("MOBILE_VERIFIED"),
    PRE_MARKET_PLACE("PRE_MARKET_PLACE"),
    MARKET_PLACE("MARKET_PLACE"),
    CPA_PENDING_VERIFICATION("CPA_PENDING_VERIFICATION"),
    CPA_ON_HOLD("CPA_ON_HOLD"),
    ON_HOLD("ON_HOLD"),
    APPLICATION_CREATED("APPLICATION_CREATED"),
    CPA_REJECTED("CPA_REJECTED"),
    CPA_FI_START("CPA_FI_START"),
    CPA_FI_COMPLETED("CPA_FI_COMPLETED"),
    CIBIL_REJECTED("CIBIL_REJECTED"),
    SELFIE_VERIFICATION_PENDING("SELFIE_VERIFICATION_PENDING")
}