package com.example.flightsearch.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class SearchPreferences(
    val searchValue: String = "",
)

class FlightPreferencesRepository(
    private val dataStore: DataStore<Preferences>
){
    val isLinearLayout: Flow<SearchPreferences> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            SearchPreferences(
                searchValue = preferences[IS_SEARCH] ?: ""
            )
        }


    private companion object {
        val IS_SEARCH = stringPreferencesKey("search")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveLayoutPreference(isSearch: String) {
        dataStore.edit { preferences ->
            preferences[IS_SEARCH] = isSearch
        }
    }
}