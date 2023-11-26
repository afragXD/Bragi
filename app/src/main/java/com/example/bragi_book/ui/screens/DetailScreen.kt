package com.example.bragi_book.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bragi_book.ui.BookDetailUiState
import com.example.bragi_book.ui.BooksUiState


@Composable
fun DetailScreen(
    booksDetailUiState: BookDetailUiState,
    retryAction: () -> Unit,
    modifier: Modifier,
    navController: NavController,
) {
    //Log.d("GG1", "DetailScreen")
    when (booksDetailUiState) {
        is BookDetailUiState.Loading -> LoadingScreen(modifier)
        is BookDetailUiState.Success -> BookDetailScreen(
            book = booksDetailUiState.bookDetail,
            modifier = modifier,
            navController = navController,
        )
        is BookDetailUiState.Error -> ErrorScreen(retryAction = retryAction, modifier)
    }
}