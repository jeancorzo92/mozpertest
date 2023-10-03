package com.jeancorzo.rickandmorty.episodes.repository

import androidx.paging.PagingData
import com.jeancorzo.rickandmorty.episodes.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepositoryAPI {

    fun getEpisodeList(): Flow<PagingData<Episode>>

}