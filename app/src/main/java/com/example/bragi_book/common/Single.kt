package com.example.bragi_book.common

class Single private constructor() {


    var bookId = 1
    var bookRuName = "Название"
    var bookName = "warlock_of_the_magus_world"
    var chapterNumber = "1"
    var chapterStr: String? = null

    companion object {
        @Volatile
        private var instance: Single? = null


        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Single().also { instance = it }
            }
    }
}