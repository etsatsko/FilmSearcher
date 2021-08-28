package com.example.filmsearcher.manager

import android.content.Context
import com.example.filmsearcher.model.Movie
import com.example.filmsearcher.model.WatchedMovie
import com.example.filmsearcher.model.db.MovieDao
import com.example.filmsearcher.model.db.AppDatabase
import com.example.filmsearcher.model.db.WatchedMovieDao
import com.example.filmsearcher.network.ApiService

class MovieManager(context: Context) {
    private var watchedMovieDao: WatchedMovieDao? = AppDatabase.createDb(context).watchedMovieDao()
    private var movieDao: MovieDao? = AppDatabase.createDb(context).movieDao()

    fun markWatchedMovie(item: Movie) {
        val movie = movieDao?.getMovieById(item.id)
        if (movie != null) {
            val watchedMovie = watchedMovieDao?.getWatchedMovie(item.id)
            if (watchedMovie == null) {
                watchedMovieDao?.insert(WatchedMovie(movie.id))
            } else {
                watchedMovieDao?.deleteMovie(watchedMovie.id)
            }
        }
    }

    suspend fun downloadMoviesByName(movieName: String): List<Movie> {
        val listResponse = ApiService.instance().getMovieByName(movieName)
        val movies = listResponse.body()?.movieList!!

        for(movie in movies) {
            if (movie.poster_path == null)
                movie.poster_path = ""
        }

        saveMoviesToDb(movies)
        return movies
    }

    fun getMoviesByName(movieName: String): List<Movie>? {
        return movieDao?.getMovieLikeTitle(movieName)
    }

    private fun saveMoviesToDb(movies: List<Movie>) {
        for (movie in movies) {
            val movieList = movieDao?.getMovieLikeTitle(movie.title)
            if (movieList.isNullOrEmpty()) {
                movieDao!!.insert(movie)
            }
        }
    }

    fun getLikedMovies(): List<Movie>? {
        return convertWatchedMoviesToMovies(watchedMovieDao?.getWatchedMovies())
    }

    private fun convertWatchedMoviesToMovies(watchedMovies: List<WatchedMovie>?): List<Movie> {
        val movies = mutableListOf<Movie>()
        if (watchedMovies != null) {
            for (movie in watchedMovies) {
                movies.add(movieDao!!.getMovieById(movie.movie_id))
            }
        }
        return movies
    }
}