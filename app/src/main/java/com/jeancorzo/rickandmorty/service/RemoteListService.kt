package com.jeancorzo.rickandmorty.service

import com.jeancorzo.rickandmorty.service.dto.ListDto

interface RemoteListService<T : ListDto<*>> {
    suspend fun getList(pageNumber: Int): T
}