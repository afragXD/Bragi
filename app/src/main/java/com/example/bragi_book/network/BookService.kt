package com.example.bragi_book.network


import com.example.bragi_book.data.BookDetail
import com.example.bragi_book.data.BooksMenu
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("books/menu")
    suspend fun bookMenu(
        @Query("limit") limit: Int,
        @Query("sort") sort: String
    ): List<BooksMenu>

    @GET("books/{id}/detail")
    suspend fun BookDetails(
        @Path("id") id: Int
        //@Query("id") id: Int,
    ): BookDetail

    @GET("books/search")
    suspend fun bookSearch(
        @Query("name") name: String,
        @Query("limit") limit: Int,
        @Query("sort") sort: String
    ): List<BooksMenu>

    //@GET("static/books/return_of_the_mount_hua_sect/1.txt")
    @Headers("Content-Type: application/json")
    @GET("static/books/{name}/{chapter}")
    suspend fun bookChapter(
        @Path("name") name: String,
        @Path("chapter") chapter: String
    ): ResponseBody
}