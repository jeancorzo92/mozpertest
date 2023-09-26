package com.jeancorzo.rickandmorty.service.di

import com.jeancorzo.rickandmorty.service.RetrofitServiceGenerator
import com.jeancorzo.rickandmorty.service.ServiceGenerator
import org.koin.dsl.module

val serviceModule = module {
    single<ServiceGenerator> { RetrofitServiceGenerator }
}