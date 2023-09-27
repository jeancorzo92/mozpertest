package com.jeancorzo.rickandmorty.characters.repository

import androidx.paging.PagingData
import com.jeancorzo.rickandmorty.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepositoryAPI {
    fun getCharacterList(): Flow<PagingData<Character>>
}