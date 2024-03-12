package com.example.madmid.composables

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorePreferencesManager(context: Context) {

    // wrapped in companion so that data store is not initialized more than once
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
    }
    val prefs = context.dataStore;

    suspend fun saveData(key: String, value: String){
        prefs.edit {
            val prefKey = stringPreferencesKey(key);
            it[prefKey] = value
        }
    }

    suspend fun getData(key: String): String {
        val keyFlow = prefs.data.map {
            it[stringPreferencesKey(key)] ?: ""
        }

        return keyFlow.first()
    }
}