package com.example.flightsearch

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearch.data.AppContainer
import com.example.flightsearch.data.AppDataContainer
import com.example.flightsearch.data.FlightPreferencesRepository

private const val SEARCH_PREFERENCE_NAME = "search_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SEARCH_PREFERENCE_NAME
)
class FlightApplication: Application() {
    lateinit var flightPreferencesRepository: FlightPreferencesRepository
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        flightPreferencesRepository = FlightPreferencesRepository(dataStore)
        container = AppDataContainer(this)
    }
}