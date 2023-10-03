package com.jeancorzo.rickandmorty.episodes.service

import com.jeancorzo.rickandmorty.service.RemoteListService
import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeListDto

class EpisodeListRemoteService(private val episodesApiService: EpisodesApiService) :
    RemoteListService<EpisodeListDto> {

    override suspend fun getList(pageNumber: Int): EpisodeListDto {
        return episodesApiService.getEpisodeList(pageNumber)
    }

}