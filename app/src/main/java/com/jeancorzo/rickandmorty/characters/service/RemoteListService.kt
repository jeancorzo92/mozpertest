package com.jeancorzo.rickandmorty.characters.service

import com.jeancorzo.rickandmorty.characters.service.dto.ListDto

interface RemoteListService<T : ListDto<*>> {
    suspend fun getList(pageNumber: Number): T
}