package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibiansPhotosRepository: AmphibiansPhotosRepository
}

class DefaultAppContainer: AppContainer{
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: AmphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansPhotosRepository: AmphibiansPhotosRepository by lazy {
        NetworkAmphibiansPhotosRepository(retrofitService)
    }
}