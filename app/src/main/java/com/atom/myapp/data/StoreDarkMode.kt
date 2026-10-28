package com.atom.myapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.darkModeDataStore: DataStore<Preferences> by preferencesDataStore(name = "dark_mode_prefs")

class StoreDarkMode(private val context: Context) {
    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")
    }

    val getDarkMode: Flow<Boolean> = context.darkModeDataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }

    suspend fun saveDarkMode(enabled: Boolean) {
        context.darkModeDataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }
}