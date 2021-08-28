package com.example.filmsearcher.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.filmsearcher.model.WatchedMovie;


@Dao
interface WatchedMovieDao {
    @Insert
    fun insert(watchedMovie: WatchedMovie)

    @Query("DELETE FROM watched_movie")
    fun deleteAll()

    @Query("DELETE FROM watched_movie WHERE id = :id")
    fun deleteMovie(id : Int)

    @Query("SELECT * FROM watched_movie")
    fun getWatchedMovies() : List<WatchedMovie>

    @Query("SELECT * FROM watched_movie WHERE movie_id = :id LIMIT 1")
    fun getWatchedMovie(id: Int) : WatchedMovie
}
