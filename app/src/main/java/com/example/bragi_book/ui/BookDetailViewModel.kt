package com.example.bragi_book.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bragi_book.BooksApplication
import com.example.bragi_book.common.Single
import com.example.bragi_book.data.BookDetRepository
import com.example.bragi_book.data.BookDetail
import com.example.bragi_book.data.BooksMenu
import com.example.bragi_book.data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookDetailUiState {
    data class Success(val bookDetail: BookDetail) : BookDetailUiState
    object Error : BookDetailUiState
    object Loading : BookDetailUiState
}

class BookDetailViewModel(
    private val booksRepository: BookDetRepository
) : ViewModel() {

    var bookDetailUiState: BookDetailUiState by mutableStateOf(BookDetailUiState.Loading)
        private set


    init {
        getDetail()
    }

    fun getDetail(
        //id: Int
    ) {
        Log.d("GG1", "XDDggggg")
        viewModelScope.launch {
            val id = Single.getInstance().bookId
            Log.d("GG1", id.toString())
            bookDetailUiState = BookDetailUiState.Loading
            bookDetailUiState =
                try {
                    BookDetailUiState.Success(booksRepository.getBookDetails(
                        id
                    ))
            } catch (e: IOException) {
                    Log.d("GG1", e.message.toString())
                    BookDetailUiState.Error
            } catch (e: HttpException) {
                    Log.d("GG1", e.message.toString())
                    BookDetailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.booksDetRepository
                BookDetailViewModel(booksRepository = booksRepository)
            }
        }
    }
}
