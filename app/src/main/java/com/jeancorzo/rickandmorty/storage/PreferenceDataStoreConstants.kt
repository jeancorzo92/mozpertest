package com.jeancorzo.rickandmorty.storage

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceDataStoreConstants {
    val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
}