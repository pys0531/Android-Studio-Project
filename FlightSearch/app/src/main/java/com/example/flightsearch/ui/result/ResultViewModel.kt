package com.example.flightsearch.ui.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightApplication
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightRepository
import com.example.flightsearch.ui.FavoriteListUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ResultViewModel(
    savedStateHandle: SavedStateHandle,
    private val flightRepository: FlightRepository
): ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ResultDestination.resultIdArg])

    var uiState by mutableStateOf(ResultUiState())
        private set
    //val uiState: StateFlow<ResultUiState> = _uiState.asStateFlow()

    var favoriteUiState: StateFlow<FavoriteListUiState> =
        flightRepository.getAllFavoriteStream()
            .map { FavoriteListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = (FavoriteListUiState())
            )

    fun insertFavorite(departureCode: String, destinationCode: String){
        viewModelScope.launch {
            val favorite: Favorite = flightRepository.getFavorite(departureCode, destinationCode)

            if (favorite == null){
                uiState = uiState.copy(
                    isFavorite = true
                )
                flightRepository.insertFavorite(
                    Favorite(
                        departureCode = departureCode,
                        destinationCode = destinationCode,
                    )
                )
            }
            else{
                uiState = uiState.copy(
                    isFavorite = false
                )
                flightRepository.deleteFavorite(
                    Favorite(
                        id = favorite.id,
                        departureCode = departureCode,
                        destinationCode = destinationCode
                    )
                )
            }
        }
    }

    fun processAirportList(id: Int){
        viewModelScope.launch{
            val selectedAirport = flightRepository.getAirportByIdStream(id).first()
            val allAirportList = flightRepository.getAllAirportStream().first()

            uiState = uiState.copy(
                departureCode = selectedAirport,
                destinationCode = allAirportList,
            )
        }
    }

    init{
        processAirportList(itemId)
    }


    companion object{
        private const val TIMEOUT_MILLIS = 5_000L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                val flightRepository = application.container.flightRepository
                ResultViewModel(
                    savedStateHandle = this.createSavedStateHandle(),
                    flightRepository = flightRepository
                )
            }
        }
    }
}