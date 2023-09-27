package com.jeancorzo.rickandmorty.characters.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import retrofit2.HttpException
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val database: AppDatabase,
    private val characterService: CharactersApiService
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = database.characterDao()
    private var nextPageNumber = 1

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val pageNumber = when (loadType) {
                LoadType.REFRESH -> nextPageNumber
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    if (nextPageNumber == -1) return MediatorResult.Success(endOfPaginationReached = true)
                    nextPageNumber
                }
            }

            val response = characterService.getCharacterList(pageNumber)
            nextPageNumber = getPageNumberFromUrl(response.info.next)

            database.withTransaction {
                characterDao.insertAll(response.toCharacterEntityList())
            }

            MediatorResult.Success(endOfPaginationReached = nextPageNumber == -1)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageNumberFromUrl(url: String?): Int {
        return if (url.isNullOrBlank()) -1
        else url.substringAfterLast("page=").toInt()
    }

}