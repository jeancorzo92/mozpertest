package com.jeancorzo.rickandmorty.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "PreferenceDataStore")

class PreferenceDataStore(context: Context): PreferenceDataStoreApi {

    private val dataSource = context.dataStore
    override suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T): T {
        return dataSource.data.first()[key] ?: defaultValue
    }

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        dataSource.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun clearPreferences() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }

}