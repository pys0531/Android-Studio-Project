package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


interface AppContainer{
    val booksRepository: BooksRepository
}

class DefaultAppContainer: AppContainer{
    private val BASE_URL = "https://www.googleapis.com/books/v1/" //volumes?q=cicero"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
//    val json = Json { ignoreUnknownKeys = true }
//    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//        .baseUrl(BASE_URL)
//        .build()
//
//    private val retrofitService: BookshelfApiService by lazy{
//        retrofit.create(BookshelfApiService::class.java)
//    }

    val bookshelfApiService: BookshelfApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(json
//                    .asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
            .create()
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(bookshelfApiService)
    }

}