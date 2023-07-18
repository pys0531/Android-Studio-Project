package com.example.flightsearch.data

import com.example.flightsearch.ui.navigation.NavigationDestination
import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(private val flightDao: FlightDao): FlightRepository {
    override fun getAllAirportStream(): Flow<List<Airport>> = flightDao.getAllAirport()
    override fun getAirportStream(query: String): Flow<List<Airport>> = flightDao.getAirport(query)
    override fun getAirportByIdStream(id: Int) : Flow<Airport> = flightDao.getAirportById(id)
    override suspend fun insertAirport(search: Airport) = flightDao.insertAirport(search)
    override suspend fun updateAirport(search: Airport) = flightDao.updateAirport(search)
    override suspend fun deleteAirport(search: Airport) = flightDao.deleteAirport(search)

    override fun getAllFavoriteStream(): Flow<List<Favorite>> = flightDao.getAllFavorite()
    override fun getFavoriteStream(id: Int): Flow<Favorite> = flightDao.getFavoriteById(id)
    override suspend fun getFavorite(departure: String, destination: String): Favorite = flightDao.getFavorite(departure, destination)
    override suspend fun insertFavorite(search: Favorite) = flightDao.insertFavorite(search)
    override suspend fun updateFavorite(search: Favorite) = flightDao.updateFavorite(search)
    override suspend fun deleteFavorite(search: Favorite) = flightDao.deleteFavorite(search)
}