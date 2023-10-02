package com.jeancorzo.rickandmorty.characters.service

import com.jeancorzo.rickandmorty.characters.service.dto.characters.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {
    @GET("character")
    suspend fun getCharacterList(@Query("page") pageNumber: Int): CharacterListDto
}