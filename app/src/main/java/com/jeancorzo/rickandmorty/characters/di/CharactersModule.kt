package com.jeancorzo.rickandmorty.characters.di

import com.jeancorzo.rickandmorty.characters.repository.CharacterRemoteMediator
import com.jeancorzo.rickandmorty.characters.repository.CharacterRepository
import com.jeancorzo.rickandmorty.characters.repository.CharacterRepositoryAPI
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import com.jeancorzo.rickandmorty.characters.ui.CharactersViewModel
import com.jeancorzo.rickandmorty.service.ServiceGenerator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(get()) }
    factory { get<ServiceGenerator>().createService(CharactersApiService::class.java) }
    factory { CharacterRemoteMediator(get(), get(), get()) }
    factory<CharacterRepositoryAPI> { CharacterRepository(get(), get()) }
}