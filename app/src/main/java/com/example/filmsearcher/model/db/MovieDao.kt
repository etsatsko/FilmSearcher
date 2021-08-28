package com.example.filmsearcher.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.filmsearcher.model.Movie


@Dao
interface MovieDao {
    @Insert
    fun insert(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getmovies() : List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id : Int) : Movie

    @Query("DELETE FROM movie")
    fun deleteAll()

    @Query("DELETE FROM movie WHERE id = :id")
    fun deletemovieById(id : Int)

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun getMovieLikeTitle(title : String) : List<Movie>
}
