package com.example.flightsearch.ui.result

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite

data class ResultUiState (
    val code: String = "",
    val departureCode: Airport = Airport(),
    val destinationCode: List<Airport> = emptyList(),
    val isFavorite: Boolean = false
)

//data class FavoriteUiState(
//    val id: Int = 0,
//    val departureCode: String = "",
//    val destinationCode: String = "",
//    val isFavorite: Boolean = false
//)
//
//fun FavoriteUiState.toFavorite(): Favorite = Favorite(
//    id = id,
//    departureCode = departureCode,
//    destinationCode = departureCode,
//)
//
//fun Favorite.toFavoriteUiState(): FavoriteUiState = FavoriteUiState(
//    id = id,
//    departureCode = departureCode,
//    destinationCode = departureCode,
//    isFavorite = true
//)