package com.jeancorzo.rickandmorty.characters.service

import com.jeancorzo.rickandmorty.characters.service.dto.CharacterListDto
import com.jeancorzo.rickandmorty.service.RemoteListService

class CharacterRemoteListService(private val charactersApiService: CharactersApiService) :
    RemoteListService<CharacterListDto> {
    override suspend fun getList(pageNumber: Int): CharacterListDto {
        return charactersApiService.getCharacterList(pageNumber)
    }
}