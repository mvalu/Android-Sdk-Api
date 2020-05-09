package com.mvalu.bettr_api.injection.component

import com.mvalu.bettr_api.BettrApiSdk
import com.mvalu.bettr_api.injection.module.NetworkModule
import com.mvalu.bettr_api.login.TokenGeneration
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
}