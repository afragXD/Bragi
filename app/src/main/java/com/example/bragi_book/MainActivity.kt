package com.example.bragi_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bragi_book.ui.BooksApp
import com.example.bragi_book.ui.theme.BragiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BragiTheme {
                BooksApp()
            }
        }
    }
}
