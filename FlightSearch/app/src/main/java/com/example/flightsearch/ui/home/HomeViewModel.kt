package com.example.flightsearch.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.FlightRepository
import com.example.flightsearch.data.FlightPreferencesRepository
import com.example.flightsearch.ui.FavoriteListUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val flightPreferencesRepository: FlightPreferencesRepository,
    private val flightRepository: FlightRepository
): ViewModel(){

    private var getAirportsJob: Job? = null

    var favoriteUiState: StateFlow<FavoriteListUiState> =
        flightRepository.getAllFavoriteStream()
            .map { FavoriteListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoriteListUiState()
            )

//    val searchPreferences: StateFlow<String> =
//        flightPreferencesRepository.isLinearLayout.map { isLinearLayout ->
//            isLinearLayout
//        }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = ""
//            )

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            flightPreferencesRepository.isLinearLayout.collect{
                homeUiState = homeUiState.copy(
                    searchQuery = it.searchValue
                )
            }
        }
    }


    fun isFavorite(departure: String, destination: String){
        viewModelScope.launch {
            val f = flightRepository.getFavorite(departure, destination)
            flightRepository.deleteFavorite(f)
        }
    }


    fun updateQuery(query: HomeUiState){
        homeUiState = query.copy(searchQuery = query.searchQuery)

        getAirportsJob?.cancel()
        getAirportsJob = flightRepository.getAirportStream(query.searchQuery)
            .onEach { result ->
                homeUiState = query.copy(
                    airportListUiState = result
                )
            }.launchIn(viewModelScope)

        updatePreferencesSearchValue(query.searchQuery)
    }

    fun updatePreferencesSearchValue(query: String){
        viewModelScope.launch {
            flightPreferencesRepository.saveLayoutPreference(query)
        }
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                val flightPreferencesRepository = application.flightPreferencesRepository
                val flightRepository = application.container.flightRepository
                HomeViewModel(
                    flightPreferencesRepository = flightPreferencesRepository,
                    flightRepository = flightRepository
                )
            }
        }
    }

}
