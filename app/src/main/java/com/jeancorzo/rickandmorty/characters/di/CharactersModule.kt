package com.jeancorzo.rickandmorty.characters.di

import com.jeancorzo.rickandmorty.characters.ui.CharactersViewModel
import com.jeancorzo.rickandmorty.characters.repository.CharactersRepository
import com.jeancorzo.rickandmorty.characters.repository.CharactersRepositoryAPI
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(get()) }
    factory<CharactersRepositoryAPI> { CharactersRepository() }
}