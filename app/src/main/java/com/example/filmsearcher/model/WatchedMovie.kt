package com.example.filmsearcher.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "watched_movie", foreignKeys = [
        ForeignKey(entity = Movie::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movie_id"),
            onDelete = CASCADE)]
)
data class WatchedMovie (
    @ColumnInfo(name = "movie_id")
    val movie_id: Int,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)