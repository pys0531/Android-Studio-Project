package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.MyCity
import com.example.mycity.data.DataSource
import com.example.mycity.model.MenuItem
import com.example.mycity.model.PlaceUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlaceViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(
        PlaceUiState(
            city = DataSource.defaultCity,
            recommend = DataSource.defaultRecommend
        )
    )
    val uiState: StateFlow<PlaceUiState> = _uiState.asStateFlow()

    fun resetRecommend(){
        _uiState.value = PlaceUiState(
            city = DataSource.defaultCity,
            recommend = DataSource.defaultRecommend
        )
    }

    fun updateCity(selectCity: MenuItem){
        updateItem(selectCity)
    }

    fun updateRecommend(selectRecomend: MenuItem){
        updateItem(selectRecomend)
    }

    private fun updateItem(newItem: MenuItem){
        _uiState.update { currenItem ->
            currenItem.copy(
                city = if (newItem is MenuItem.City) newItem else currenItem.city,
                recommend = if (newItem is MenuItem.Recommend) newItem else currenItem.recommend
            )
        }
    }
}