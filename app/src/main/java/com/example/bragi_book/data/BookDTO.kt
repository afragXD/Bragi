package com.example.bragi_book.data


data class BooksMenu(
    val id: Int,
    val description: String,
    val name: String,
    val image: String,
    val rating: Float,
    val status: String,
)

data class BookDetail(
    val name: String,
    val enName: String,
    val image: String,
    val description: String,
    val rating: Float,
    val status: String,
    val chapters: Short,
    val year: Short,
    val author: String,
)