package com.mvalu.bettr_api.injection.component

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.account_statements.AccountStatements
import com.mvalu.bettr_api.application_journey.ApplicationJourney
import com.mvalu.bettr_api.card_user.CardUser
import com.mvalu.bettr_api.downloads.DocumentDownload
import com.mvalu.bettr_api.emi.Emi
import com.mvalu.bettr_api.home_module.HomeModule
import com.mvalu.bettr_api.injection.module.NetworkModule
import com.mvalu.bettr_api.login.TokenGeneration
import com.mvalu.bettr_api.payment.Payment
import com.mvalu.bettr_api.rewards.Rewards
import com.mvalu.bettr_api.search.SearchFieldData
import com.mvalu.bettr_api.settings.Settings
import com.mvalu.bettr_api.settings.otp.OTP
import com.mvalu.bettr_api.settings.plastic_card.PlasticCard
import com.mvalu.bettr_api.transactions.CardTransactions
import com.mvalu.bettr_api.upi.UPIPayment
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(apiSdk: BettrApiSdk)
    fun inject(tokenGeneration: TokenGeneration)
    fun inject(cardUser: CardUser)
    fun inject(homeModule: HomeModule)
    fun inject(cardTransactions: CardTransactions)
    fun inject(accountStatements: AccountStatements)
    fun inject(applicationJourney: ApplicationJourney)
    fun inject(documentDownload: DocumentDownload)
    fun inject(rewards: Rewards)
    fun inject(settings: Settings)
    fun inject(payment: Payment)
    fun inject(otp: OTP)
    fun inject(plasticCard: PlasticCard)
    fun inject(searchFieldData: SearchFieldData)
    fun inject(upiPayment: UPIPayment)
    fun inject(emi: Emi)
}