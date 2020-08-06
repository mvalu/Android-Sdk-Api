package com.mvalu.bettr_api.account_statements.transactions

enum class TransactionStatus {
    AUTH_APPROVED,
    AUTH_REJECTED,
    SUCCESS,
    FAILED,
    REVERSED,
    MERCHANT_CANCELLED,
    CONVERTED_TO_EMI
}