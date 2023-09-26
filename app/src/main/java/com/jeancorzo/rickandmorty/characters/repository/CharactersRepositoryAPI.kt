package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.model.Character

interface CharactersRepositoryAPI {
    suspend fun getCharacterList(): List<Character>
    suspend fun getCharacter(characterId: Long): Character
}