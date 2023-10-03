package com.jeancorzo.rickandmorty.episodes.service

import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeListDto
import retrofit2.http.GET

interface EpisodesApiService {

    @GET("episode")
    suspend fun getEpisodeList(pageNumber: Int): EpisodeListDto

}