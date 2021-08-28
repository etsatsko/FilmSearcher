package com.example.filmsearcher.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmsearcher.model.Movie
import com.example.filmsearcher.model.WatchedMovie

@Database(entities = arrayOf(Movie::class, WatchedMovie::class), version = 4)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun watchedMovieDao(): WatchedMovieDao

    companion object {
        fun createDb(contex: Context) =
            Room.databaseBuilder(contex, AppDatabase::class.java, "filmsearcher").fallbackToDestructiveMigration().build()
    }
}