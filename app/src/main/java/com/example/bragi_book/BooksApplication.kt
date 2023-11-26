package com.example.bragi_book

import android.app.Application
import com.example.bragi_book.data.AppContainer
import com.example.bragi_book.data.DefaultAppContainer


class BooksApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}