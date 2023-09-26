package com.jeancorzo.rickandmorty.characters.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jeancorzo.rickandmorty.characters.model.Character
import com.jeancorzo.rickandmorty.characters.service.CharactersApiService
import kotlinx.coroutines.delay

private const val PAGE_STEP = 1

class CharacterPagingSource(
    private val charactersApiService: CharactersApiService
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        delay(2000)
        return try {
            val nextPageNumber = params.key ?: 1
            val response = charactersApiService.getCharacterList(nextPageNumber)
            val characterList = response.toCharacterList()
            val nextKey: Int? = if (nextPageNumber + PAGE_STEP < response.info.pages) {
                nextPageNumber + PAGE_STEP
            } else null
            LoadResult.Page(
                characterList,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}