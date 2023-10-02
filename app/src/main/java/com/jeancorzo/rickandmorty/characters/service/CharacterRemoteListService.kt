package com.jeancorzo.rickandmorty.characters.service

import com.jeancorzo.rickandmorty.characters.service.dto.characters.CharacterListDto

class CharacterRemoteListService(private val charactersApiService: CharactersApiService) :
    RemoteListService<CharacterListDto> {
    override suspend fun getList(pageNumber: Int): CharacterListDto {
        return charactersApiService.getCharacterList(pageNumber)
    }
}