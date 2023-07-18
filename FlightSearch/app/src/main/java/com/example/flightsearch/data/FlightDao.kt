package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    // Airport
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAirport(search: Airport)
    @Update
    suspend fun updateAirport(search: Airport)
    @Delete
    suspend fun deleteAirport(search: Airport)
    @Query("SELECT * FROM airport ORDER BY name ASC")
    fun getAllAirport(): Flow<List<Airport>>
    @Query("SELECT * FROM airport WHERE id = :id")
    fun getAirportById(id: Int): Flow<Airport>
    @Query("SELECT * FROM airport " +
            "WHERE iata_code LIKE '%' || :query || '%' OR name LIKE '%' || :query || '%' " +
            "ORDER BY name")
    fun getAirport(query: String): Flow<List<Airport>>


    // Favorite
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(search: Favorite)
    @Update
    suspend fun updateFavorite(search: Favorite)
    @Delete
    suspend fun deleteFavorite(search: Favorite)
    @Query("SELECT * FROM favorite ORDER BY departure_code ASC")
    fun getAllFavorite(): Flow<List<Favorite>>
    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavoriteById(id: Int): Flow<Favorite>
    @Query("SELECT * FROM favorite " +
            "WHERE departure_code = :departure " +
            "AND destination_code = :destination")
    suspend fun getFavorite(departure: String, destination: String): Favorite
}
