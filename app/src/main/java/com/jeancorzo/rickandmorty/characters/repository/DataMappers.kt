package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.domain.model.Character
import com.jeancorzo.rickandmorty.characters.service.dto.CharacterDto
import com.jeancorzo.rickandmorty.characters.service.dto.CharacterListDto
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity
fun CharacterListDto.toCharacterEntityList(): List<CharacterEntity> {
    return this.data.map { it.toCharacterEntity() }
}

fun CharacterDto.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        this.id,
        this.name,
        this.status,
        this.species,
        this.gender,
        this.origin.name,
        this.location.name,
        this.image,
        this.episode.size
    )
}

fun CharacterEntity.toCharacter() : Character {
    return Character(
        this.id,
        this.name,
        this.status,
        this.species,
        this.gender,
        this.origin,
        this.location,
        this.imageUrl,
        this.numberOfEpisodes
    )
}

