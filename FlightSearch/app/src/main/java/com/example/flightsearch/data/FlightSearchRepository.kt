package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    // Airport
    fun getAllAirportStream(): Flow<List<Airport>>
    fun getAirportStream(query: String): Flow<List<Airport>>
    fun getAirportByIdStream(id: Int): Flow<Airport>
    suspend fun insertAirport(search: Airport)
    suspend fun updateAirport(search: Airport)
    suspend fun deleteAirport(search: Airport)

    // Favorite
    fun getAllFavoriteStream(): Flow<List<Favorite>>
    fun getFavoriteStream(id: Int): Flow<Favorite>
    suspend fun getFavorite(departure: String, destination: String): Favorite
    suspend fun insertFavorite(search: Favorite)
    suspend fun updateFavorite(search: Favorite)
    suspend fun deleteFavorite(search: Favorite)
}