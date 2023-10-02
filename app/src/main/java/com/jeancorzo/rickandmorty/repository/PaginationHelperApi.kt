package com.jeancorzo.rickandmorty.repository

import com.jeancorzo.rickandmorty.characters.service.dto.InfoDto

interface PaginationHelperApi {

    fun getFirstPageNumber(): Int

    fun getNextPageNumber(): Int

    fun updatePageNumber(info: InfoDto)

    fun hasNextPage() : Boolean
}