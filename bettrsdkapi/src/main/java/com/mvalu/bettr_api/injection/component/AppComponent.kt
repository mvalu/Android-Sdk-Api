package com.mvalu.bettr_api.injection.component

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.account_statements.AccountStatements
import com.mvalu.bettr_api.application_journey.ApplicationJourney
import com.mvalu.bettr_api.card_user.CardUser
import com.mvalu.bettr_api.home_module.HomeModule
import com.mvalu.bettr_api.injection.module.NetworkModule
import com.mvalu.bettr_api.login.TokenGeneration
import com.mvalu.bettr_api.transactions.CardTransactions
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
}