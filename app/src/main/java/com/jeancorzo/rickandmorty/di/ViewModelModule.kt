package com.jeancorzo.rickandmorty.di

import com.jeancorzo.rickandmorty.login.LoginViewModel
import com.jeancorzo.rickandmorty.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
}