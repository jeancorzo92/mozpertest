package com.jeancorzo.rickandmorty.characters.service

import com.jeancorzo.rickandmorty.characters.service.dto.CharacterListDto
import retrofit2.http.GET

interface CharactersApiService {
    @GET("character")
    suspend fun getCharacterList(): CharacterListDto
}