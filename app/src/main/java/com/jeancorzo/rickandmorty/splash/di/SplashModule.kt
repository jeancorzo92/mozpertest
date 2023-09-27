package com.jeancorzo.rickandmorty.splash.di

import com.jeancorzo.rickandmorty.splash.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val splashModule = module {
    viewModelOf(::SplashViewModel)
}