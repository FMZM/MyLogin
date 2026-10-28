package com.atom.myapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class StoreUserMail(private val context: Context) {

    companion object {
        val EMAIL_KEY = stringPreferencesKey("user_email")
        val PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    val getMail: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }

    val getPassword: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PASSWORD_KEY] ?: ""
        }

    suspend fun saveUser(email: String, pass: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = pass
        }
    }
}