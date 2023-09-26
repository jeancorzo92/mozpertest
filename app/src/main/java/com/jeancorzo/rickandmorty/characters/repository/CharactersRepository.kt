package com.jeancorzo.rickandmorty.characters.repository

import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.model.rick

class CharactersRepository : CharactersRepositoryAPI {
    override suspend fun getCharacterList(): List<Character> {
        return listOf(Character.rick(), Character.rick(), Character.rick())
    }

    override suspend fun getCharacter(characterId: Long): Character {
        return Character.rick()
    }
}


