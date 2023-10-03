package com.jeancorzo.rickandmorty.episodes.repository

import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeListDto
import com.jeancorzo.rickandmorty.repository.DataMapper
import com.jeancorzo.rickandmorty.storage.db.entities.EpisodeEntity

object EpisodeListDtoToEntityMapper : DataMapper<EpisodeListDto, List<EpisodeEntity>> {

    override fun map(source: EpisodeListDto): List<EpisodeEntity> {
        return source.toEpisodeEntityList()
    }

}