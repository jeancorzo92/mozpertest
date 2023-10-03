package com.jeancorzo.rickandmorty.characters.di

import androidx.paging.PagingDataAdapter
import com.jeancorzo.rickandmorty.characters.domain.model.Character
import com.jeancorzo.rickandmorty.characters.repository.CharacterListDtoToEntityMapper
import com.jeancorzo.rickandmorty.characters.repository.CharacterRepository
import com.jeancorzo.rickandmorty.characters.repository.CharacterRepositoryAPI
import com.jeancorzo.rickandmorty.characters.service.CharacterRemoteListService
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import com.jeancorzo.rickandmorty.characters.ui.CharacterClickListener
import com.jeancorzo.rickandmorty.characters.ui.CharacterListPagingAdapter
import com.jeancorzo.rickandmorty.characters.ui.CharactersViewModel
import com.jeancorzo.rickandmorty.repository.ListRemoteMediator
import com.jeancorzo.rickandmorty.service.ServiceGenerator
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel { CharactersViewModel(get()) }
    factory {
        get<ServiceGenerator>().createService(
            CharactersApiService::class.java
        )
    }
    factory { CharacterRemoteListService(get()) }
    factory {
        ListRemoteMediator(
            database = get(),
            dao = get<AppDatabase>().characterDao(),
            service = get<CharacterRemoteListService>(),
            paginationHelper = get(),
            dataMapper = CharacterListDtoToEntityMapper
        )
    }
    factory<CharacterRepositoryAPI> { CharacterRepository(get(), get()) }

    factory<PagingDataAdapter<Character, CharacterListPagingAdapter.CharacterViewHolder>> { (clickListener: CharacterClickListener) ->
        CharacterListPagingAdapter(clickListener)
    }
}