package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansPhoto
import com.example.amphibians.network.AmphibiansApiService


interface AmphibiansPhotosRepository {
    suspend fun getPhotos(): List<AmphibiansPhoto>
}

class NetworkAmphibiansPhotosRepository(private val amphibiansApiService: AmphibiansApiService): AmphibiansPhotosRepository{
    override suspend fun getPhotos(): List<AmphibiansPhoto> {
        return amphibiansApiService.getPhotos()
    }
}