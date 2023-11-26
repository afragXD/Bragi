package com.example.bragi_book.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bragi_book.common.Single
import com.example.bragi_book.ui.screens.ChapterScreen
import com.example.bragi_book.ui.screens.DetailScreen
import com.example.bragi_book.ui.screens.HomeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksApp(
    modifier: Modifier = Modifier
) {


    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "HomeScreen",
    ){
        composable("HomeScreen"){
            val booksViewModel: BooksViewModel =
                viewModel(factory = BooksViewModel.Factory)

            Scaffold(
                modifier = modifier.fillMaxSize(),
            ) {
                Surface(modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        booksUiState = booksViewModel.booksUiState,
                        retryAction = { booksViewModel.getMenu() },
                        modifier = modifier,
                        navController = navController,
                    )
                }
            }
        }
        composable(
            "DetailScreen",
            //arguments = listOf(
            //    navArgument(name = "id"){
            //        type = NavType.IntType
            //    }
            //)
        ){

            val booksDetailViewModel: BookDetailViewModel =
                viewModel(factory = BookDetailViewModel.Factory)

            DetailScreen(
                booksDetailUiState = booksDetailViewModel.bookDetailUiState,
                retryAction = { booksDetailViewModel.getDetail() },
                modifier = modifier,
                navController = navController,
            )
        }
        composable(
            "ChapterScreen",
            //arguments = listOf(
            //    navArgument(name = "name"){
            //        type = NavType.StringType
            //    },
            //    navArgument(name = "chapter"){
            //        type = NavType.StringType
            //    }
            //)
        ){it->
            //Single.getInstance().bookName = it.arguments?.getString("name")!!
            //Single.getInstance().chapterNumber = it.arguments?.getString("chapter")!!


            val chapterViewModel: ChapterViewModel =
                viewModel(factory = ChapterViewModel.Factory)

            ChapterScreen(
                chapterUiState = chapterViewModel.chapterUiState,
                retryAction = { chapterViewModel.getChapter() },
                modifier = modifier,
                navController = navController
            )

        }
    }


    //val booksViewModel: BooksViewModel =
    //    viewModel(factory = BooksViewModel.Factory)
//
    //Scaffold(
    //    modifier = modifier.fillMaxSize(),
    //) {
    //    Surface(modifier = modifier
    //        .fillMaxSize()
    //        .padding(it),
    //        color = MaterialTheme.colorScheme.background
    //    ) {
    //        HomeScreen(
    //            booksUiState = booksViewModel.booksUiState,
    //            retryAction = { booksViewModel.getMenu() },
    //            modifier = modifier,
    //            navController = navController,
    //        )
    //    }
    //}
}