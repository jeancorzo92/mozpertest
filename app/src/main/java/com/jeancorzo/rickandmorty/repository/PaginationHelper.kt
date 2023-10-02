package com.jeancorzo.rickandmorty.repository

import com.jeancorzo.rickandmorty.characters.service.dto.InfoDto

private const val FIRST_PAGE_NUMBER = 1

class PaginationHelper : PaginationHelperApi {

    private var currentPageNumber = FIRST_PAGE_NUMBER

    override fun getFirstPageNumber(): Int = FIRST_PAGE_NUMBER
    override fun getNextPageNumber(): Int = currentPageNumber

    override fun updatePageNumber(info: InfoDto) {
        val pageNumber = getPageNumberFromUrl(info.next)
        currentPageNumber = pageNumber
    }

    override fun hasNextPage(): Boolean = currentPageNumber >= 1

    private fun getPageNumberFromUrl(url: String?): Int {
        return if (url.isNullOrBlank()) -1
        else url.substringAfterLast("page=").toInt()
    }
}