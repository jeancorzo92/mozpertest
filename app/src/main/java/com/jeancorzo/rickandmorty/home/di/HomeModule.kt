package com.jeancorzo.rickandmorty.home.di

import com.jeancorzo.rickandmorty.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}