package com.jeancorzo.rickandmorty.login.di

import com.jeancorzo.rickandmorty.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
}