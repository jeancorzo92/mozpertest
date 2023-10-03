package com.jeancorzo.rickandmorty.episodes.repository

import com.jeancorzo.rickandmorty.episodes.domain.model.Episode
import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeDto
import com.jeancorzo.rickandmorty.episodes.service.dto.EpisodeListDto
import com.jeancorzo.rickandmorty.storage.db.entities.EpisodeEntity

fun EpisodeListDto.toEpisodeEntityList(): List<EpisodeEntity> {
    return this.data.map { it.toEntity() }
}

fun EpisodeDto.toEntity(): EpisodeEntity {
    return EpisodeEntity(
        this.id,
        this.name,
        this.type,
        this.dimension,
        this.residents.size
    )
}

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        this.id,
        this.name,
        this.type,
        this.dimension,
        this.residents
    )
}