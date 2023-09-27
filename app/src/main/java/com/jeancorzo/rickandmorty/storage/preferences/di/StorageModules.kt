package com.jeancorzo.rickandmorty.storage.preferences.di

import com.jeancorzo.rickandmorty.storage.preferences.PreferenceDataStore
import com.jeancorzo.rickandmorty.storage.preferences.PreferenceDataStoreApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single<PreferenceDataStoreApi> { PreferenceDataStore(androidContext()) }
}