package com.jeancorzo.rickandmorty.characters.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import com.jeancorzo.rickandmorty.repository.PaginationHelperApi
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import retrofit2.HttpException
import com.jeancorzo.rickandmorty.storage.db.entities.CharacterEntity
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val database: AppDatabase,
    private val characterService: CharactersApiService,
    private val paginationHelper: PaginationHelperApi
) : RemoteMediator<Int, CharacterEntity>() {

    private val characterDao = database.characterDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val pageNumber = when (loadType) {
                LoadType.REFRESH -> paginationHelper.getFirstPageNumber()
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    if (paginationHelper.hasNextPage()) return MediatorResult.Success(endOfPaginationReached = true)
                    paginationHelper.getNextPageNumber()
                }
            }

            val response = characterService.getCharacterList(pageNumber)
            paginationHelper.updatePageNumber(response.info)

            database.withTransaction {
                characterDao.insertAll(response.toCharacterEntityList())
            }

            MediatorResult.Success(endOfPaginationReached = paginationHelper.getNextPageNumber() == -1)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }



}