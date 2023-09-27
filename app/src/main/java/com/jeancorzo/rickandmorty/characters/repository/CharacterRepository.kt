package com.jeancorzo.rickandmorty.characters.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.map
import com.jeancorzo.rickandmorty.characters.domain.model.Character
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 12
@OptIn(ExperimentalPagingApi::class)
class CharacterRepository(
    private val appDatabase: AppDatabase,
    private val remoteMediator: CharacterRemoteMediator
) : CharacterRepositoryAPI {

    override fun getCharacterList(): Flow<PagingData<Character>> {
        val pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }

        return Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toCharacter() }
        }
    }

}