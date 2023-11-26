package com.example.bragi_book.data

import com.example.bragi_book.network.BookService
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import java.net.URL


interface BooksRepository {
    suspend fun getBooks(
        limit: Int, sort: String
    ) : List<BooksMenu>

}

interface BookDetRepository {

    suspend fun getBookDetails(
        id: Int
    ) : BookDetail
}

interface BookChapterRepository {

    suspend fun getBookChapter(
        name: String,
        chapter: String
    ) : ResponseBody
}

class NetworkBooksRepository(
    private val bookService: BookService
) : BooksRepository {
    override suspend fun getBooks(
        limit: Int,
        sort: String
    ): List<BooksMenu> = bookService.bookMenu(
        limit, sort
    )
}

class NetworkBookDetRepository(
    private val bookService: BookService
) : BookDetRepository {
    override suspend fun getBookDetails(
        id: Int
    ): BookDetail = bookService.BookDetails(id)
}

class NetworkBookChapterRepository(
    private val bookService: BookService
) : BookChapterRepository {
    override suspend fun getBookChapter(
        name: String,
        chapter: String
    ): ResponseBody = bookService.bookChapter(
        name, chapter
    )
}

/*
val url = URL("http://127.0.0.1:8080/static/books/{$name}/{$chapter}.txt")
return url.readText()
 */