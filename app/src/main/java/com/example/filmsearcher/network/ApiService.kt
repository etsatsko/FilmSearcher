package com.example.filmsearcher.network;

import com.example.filmsearcher.model.Movie
import com.example.filmsearcher.model.MovieList
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: String, @Query("api_key") apiKey: String = "bbaa4987bce9434394b27a1ebdfd011a") : Response<Movie>

    @GET("search/movie")
    suspend fun getMovieByName(@Query("query") name: String, @Query("api_key") apiKey: String = "bbaa4987bce9434394b27a1ebdfd011a") : Response<MovieList>

    companion object {
        const val API_URL = "https://api.themoviedb.org/3/"

        fun instance() = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}