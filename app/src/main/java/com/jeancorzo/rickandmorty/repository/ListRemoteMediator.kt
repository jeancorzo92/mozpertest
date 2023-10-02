package com.jeancorzo.rickandmorty.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jeancorzo.rickandmorty.characters.service.RemoteListService
import com.jeancorzo.rickandmorty.characters.service.dto.ListDto
import com.jeancorzo.rickandmorty.storage.db.AppDatabase
import com.jeancorzo.rickandmorty.storage.db.dao.InsertAllDao
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
open class ListRemoteMediator<T: ListDto<*>, S : Any>(
    private val database: AppDatabase,
    private val dao : InsertAllDao<S>,
    private val service: RemoteListService<T>,
    private val paginationHelper: PaginationHelperApi,
    private val dataMapper: DataMapper<T, List<S>>
) : RemoteMediator<Int, S>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, S>
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

            val response = service.getList(pageNumber)
            paginationHelper.updatePageNumber(response.info)

            database.withTransaction {
                dao.insertAll(dataMapper.map(response))
            }

            MediatorResult.Success(endOfPaginationReached = paginationHelper.getNextPageNumber() == -1)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }



}