package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.service.dto.characters.CharacterListDto
import com.jeancorzo.rickandmorty.repository.DataMapper
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity

object CharacterListDtoToEntityMapper : DataMapper<CharacterListDto, List<CharacterEntity>> {

    override fun map(source: CharacterListDto): List<CharacterEntity> {
        return source.toCharacterEntityList()
    }

}