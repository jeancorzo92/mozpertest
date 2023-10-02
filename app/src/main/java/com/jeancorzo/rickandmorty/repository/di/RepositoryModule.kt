package com.jeancorzo.rickandmorty.repository.di

import com.jeancorzo.rickandmorty.repository.PaginationHelper
import com.jeancorzo.rickandmorty.repository.PaginationHelperApi
import org.koin.dsl.module

val repositoryModule = module {
    factory<PaginationHelperApi> { PaginationHelper() }
}