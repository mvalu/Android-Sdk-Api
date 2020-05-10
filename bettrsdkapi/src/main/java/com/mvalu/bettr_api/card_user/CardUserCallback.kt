package com.mvalu.bettr_api.card_user

interface CardUserCallback {
    fun onSuccess(cardUserResponse: CardUserResponse)
    fun onError(errorMessage: String)
}