package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepositoryAPI {
    suspend fun getCharacterList(): Flow<List<Character>>
    suspend fun getCharacter(characterId: Long): Flow<Character>
}