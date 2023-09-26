package com.jeancorzo.rickandmorty.characters.di

import androidx.paging.PagingSource
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.repository.CharacterPagingSource
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import com.jeancorzo.rickandmorty.characters.ui.CharactersViewModel
import com.jeancorzo.rickandmorty.service.ServiceGenerator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(get()) }
    factory { get<ServiceGenerator>().createService(CharactersApiService::class.java) }
    factory<PagingSource<Int, Character>> { CharacterPagingSource(get()) }
}