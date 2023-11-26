package com.example.bragi_book.data

import com.example.bragi_book.common.Contains
import com.example.bragi_book.network.BookService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val booksRepository: BooksRepository
    val booksDetRepository: BookDetRepository
    val booksChapterRepository: BookChapterRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = Contains.BRAGI_URL

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(retrofitService)
    }
    override val booksDetRepository: BookDetRepository by lazy {
        NetworkBookDetRepository(retrofitService)
    }
    override val booksChapterRepository: BookChapterRepository by lazy {
        NetworkBookChapterRepository(retrofitService)
    }
}