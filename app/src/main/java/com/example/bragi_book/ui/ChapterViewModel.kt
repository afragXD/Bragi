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
import com.example.bragi_book.data.BookChapterRepository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.URL

sealed interface ChapterUiState {
    data class Success(val chapterSearch: ResponseBody) : ChapterUiState
    object Error : ChapterUiState
    object Loading : ChapterUiState
}

class ChapterViewModel(
    private val bookChapterRepository: BookChapterRepository
) : ViewModel() {

    var chapterUiState: ChapterUiState by mutableStateOf(ChapterUiState.Loading)
        private set


    init {
        getChapter()
    }

    fun getChapter(

    ) {
        viewModelScope.launch {
            val name = Single.getInstance().bookName
            val chapter = Single.getInstance().chapterNumber + ".txt"

            chapterUiState = ChapterUiState.Loading
            chapterUiState =
                try {
                    ChapterUiState.Success(
                        bookChapterRepository.getBookChapter(
                        name, chapter)
                    )
            } catch (e: IOException) {
                    Log.d("GG1", e.message!!)
                    ChapterUiState.Error
            } catch (e: HttpException) {
                    Log.d("GG1", e.message!!)
                    ChapterUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val chapterRepository = application.container.booksChapterRepository
                ChapterViewModel(bookChapterRepository = chapterRepository)
            }
        }
    }
}
