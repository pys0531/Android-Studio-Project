package com.example.bookshelf.data

import android.util.Log
import com.example.bookshelf.model.Book
import com.example.bookshelf.model.QueryResponse
import com.example.bookshelf.network.BookshelfApiService
import java.lang.Exception

interface BooksRepository{
    suspend fun getBooks(query: String): List<Book>?
    suspend fun getBook(id: String): Book?
}

class NetworkBooksRepository(private val bookshelfApiService: BookshelfApiService): BooksRepository{
    override suspend fun getBooks(query: String): List<Book>? {
        return try {
            Log.e("query", "getBooks: $query", )
            val res = bookshelfApiService.getBooks(query)
            Log.e("res", "getBooks: ${res.raw().request.url}", )
            if (res.isSuccessful)
                res.body()?.items ?: emptyList()
            else
                emptyList()
        }
        catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBook(id: String): Book?{
        return try {
            val res = bookshelfApiService.getBook(id)
            if (res.isSuccessful)
                res.body()
            else
                null
        }
        catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}