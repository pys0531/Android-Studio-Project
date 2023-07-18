package com.example.amphibians.ui.Screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansPhotosRepository
import com.example.amphibians.model.AmphibiansPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val photos: List<AmphibiansPhoto>): AmphibiansUiState
    object Loading: AmphibiansUiState
    object Error: AmphibiansUiState
}

class AmphibiansViewModel(private val amphibiansPhotosRepository: AmphibiansPhotosRepository): ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val marsPhotosRepository = application.container.amphibiansPhotosRepository
                AmphibiansViewModel(marsPhotosRepository)
            }
        }
    }

    init {
        getAmphibiansPhotos()
    }

    fun getAmphibiansPhotos(){
        viewModelScope.launch{
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try{
                AmphibiansUiState.Success(
                    amphibiansPhotosRepository.getPhotos()
                )
            }
            catch (e: IOException){
                AmphibiansUiState.Error
            }
            catch (e: HttpException){
                AmphibiansUiState.Error
            }
        }
    }
}