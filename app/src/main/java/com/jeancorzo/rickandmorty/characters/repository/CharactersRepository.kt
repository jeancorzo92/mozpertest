package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.model.rick
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersRepository(private val charactersApiService: CharactersApiService) : CharactersRepositoryAPI {


    override suspend fun getCharacterList(): Flow<List<Character>> {
        return flow {
            emit(charactersApiService.getCharacterList().toCharacterList())
        }
    }

    override suspend fun getCharacter(characterId: Long): Flow<Character> {
        return flow {
            Character.rick()
        }
    }
}


