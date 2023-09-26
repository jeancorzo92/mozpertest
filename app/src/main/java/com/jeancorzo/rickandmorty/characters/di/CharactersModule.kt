package com.jeancorzo.rickandmorty.characters.di

import com.jeancorzo.rickandmorty.characters.ui.CharactersViewModel
import com.jeancorzo.rickandmorty.characters.repository.CharactersRepository
import com.jeancorzo.rickandmorty.characters.repository.CharactersRepositoryAPI
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import com.jeancorzo.rickandmorty.service.ServiceGenerator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(get()) }
    factory<CharactersRepositoryAPI> {
        CharactersRepository(get<ServiceGenerator>().createService(CharactersApiService::class.java))
    }
}