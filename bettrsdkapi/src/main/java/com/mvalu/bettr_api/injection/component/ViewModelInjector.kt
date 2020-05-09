package com.mvalu.bettr_api.injection.component

import com.mvalu.bettr_api.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

}