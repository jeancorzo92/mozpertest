package com.jeancorzo.rickandmorty.episodes.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.jeancorzo.rickandmorty.episodes.domain.model.Episode
import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeListDto
import com.jeancorzo.rickandmorty.repository.ListRemoteMediator
import com.jeancorzo.rickandmorty.repository.PAGE_SIZE
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import com.jeancorzo.rickandmorty.storage.db.entities.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class EpisodeRepository(
    private val appDatabase: AppDatabase,
    private val remoteMediator: ListRemoteMediator<EpisodeListDto, EpisodeEntity>,
) : EpisodeRepositoryAPI {

    override suspend fun getEpisodeList(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { appDatabase.episodeDao().getAllEpisodes() }
        ).flow.map { pagingData ->
            pagingData.map { it.toEpisode() }
        }
    }
}