package com.example.bookshelf.ui.Screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookUiState{
    data class Success(val books: List<Book>): BookUiState
    object Loading: BookUiState
    object Error: BookUiState
}

class BooksViewModel(private val booksRepository: BooksRepository): ViewModel(){
    var booksUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    var searchUiState: String by mutableStateOf("")
        private set

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory{
            initializer{
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository)
            }
        }
    }

    init {
        getBookInfos()
    }

    fun updateSearch(search: String){
        searchUiState = search
    }

    fun getBookInfos(){
        viewModelScope.launch {
            booksUiState = BookUiState.Loading

            booksUiState = try{
                val books = booksRepository.getBooks(searchUiState)
                if (books == null)
                    BookUiState.Error
                else if (books.isEmpty())
                    BookUiState.Success(emptyList())
                else
                    BookUiState.Success(books)
            }
            catch (e: IOException){
                BookUiState.Error
            }
            catch (e: HttpException){
                BookUiState.Error
            }
        }
    }
}