package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.service.dto.CharacterDto
import com.jeancorzo.rickandmorty.characters.service.dto.CharacterListDto

fun CharacterListDto.toCharacterList(): List<Character> {
    return this.results.map { it.toCharacter() }
}

fun CharacterDto.toCharacter(): Character {
    return Character(
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

