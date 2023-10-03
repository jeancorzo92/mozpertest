package com.jeancorzo.rickandmorty.di

import androidx.paging.LoadStateAdapter
import com.jeancorzo.rickandmorty.utils.AppLoadStateAdapter
import com.jeancorzo.rickandmorty.utils.LoadStateViewHolder
import com.jeancorzo.rickandmorty.utils.RetryListener
import org.koin.dsl.module

val appModule = module {
    factory<LoadStateAdapter<LoadStateViewHolder>> { (retry: RetryListener) ->
        AppLoadStateAdapter(retry)
    }
}