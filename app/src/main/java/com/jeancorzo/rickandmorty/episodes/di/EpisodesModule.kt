package com.jeancorzo.rickandmorty.episodes.di

import com.jeancorzo.rickandmorty.episodes.repository.EpisodeListDtoToEntityMapper
import com.jeancorzo.rickandmorty.episodes.repository.EpisodeRepository
import com.jeancorzo.rickandmorty.episodes.repository.EpisodeRepositoryAPI
import com.jeancorzo.rickandmorty.episodes.service.EpisodeListRemoteService
import com.jeancorzo.rickandmorty.episodes.service.EpisodesApiService
import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeListDto
import com.jeancorzo.rickandmorty.repository.ListRemoteMediator
import com.jeancorzo.rickandmorty.service.ServiceGenerator
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import com.jeancorzo.rickandmorty.storage.db.entities.EpisodeEntity
import org.koin.dsl.module

val episodesModule = module {
    factory {
        get<ServiceGenerator>().createService(
            EpisodesApiService::class.java
        )
    }
    factory { EpisodeListRemoteService(get()) }
    factory {
        ListRemoteMediator<EpisodeListDto, EpisodeEntity>(
            get(),
            get<AppDatabase>().episodeDao(),
            get<EpisodeListRemoteService>(),
            get(),
            EpisodeListDtoToEntityMapper
        )
    }
    factory<EpisodeRepositoryAPI> { EpisodeRepository(get(), get()) }
}