package com.jeancorzo.rickandmorty.storage

import androidx.datastore.preferences.core.Preferences

interface PreferenceDataStoreApi {
    suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T): T
    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T)
    suspend fun clearPreferences()
}