package com.example.flightsearch.data

import android.content.Context

interface AppContainer{
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context): AppContainer{
    override val flightRepository: FlightRepository by lazy {
        OfflineFlightRepository(FlightDatabase.getDatabase(context).FlightDao())
    }
}