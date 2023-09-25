package com.jeancorzo.rickandmorty.storage.di

import com.jeancorzo.rickandmorty.storage.PreferenceDataStore
import com.jeancorzo.rickandmorty.storage.PreferenceDataStoreApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single<PreferenceDataStoreApi> { PreferenceDataStore(androidContext()) }
}