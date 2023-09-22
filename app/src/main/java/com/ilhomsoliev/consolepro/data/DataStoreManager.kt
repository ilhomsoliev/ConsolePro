package com.ilhomsoliev.consolepro.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")
        val last_time_sync_key = longPreferencesKey("last_time_sync_key")
    }

    suspend fun changeLastTimeSync(value: Long) {
        context.dataStore.edit { preferences ->
            preferences[last_time_sync_key] = value
        }
    }

    suspend fun getLastTimeSync() = withContext(IO) {
        context.dataStore.data.first()[last_time_sync_key] ?: 0L
    }
}