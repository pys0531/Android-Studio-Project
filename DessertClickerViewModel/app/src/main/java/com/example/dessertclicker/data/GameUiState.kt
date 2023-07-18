package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertLists

data class GameUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertLists[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertLists[currentDessertIndex].imageId
)
