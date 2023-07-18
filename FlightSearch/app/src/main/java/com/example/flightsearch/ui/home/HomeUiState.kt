package com.example.flightsearch.ui.home

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import kotlinx.coroutines.flow.Flow

data class HomeUiState(
    val searchQuery: String = "",
    val selectedCode: String = "",
    val airportListUiState: List<Airport> = listOf(),
    val isFavorite: Boolean = false
)

