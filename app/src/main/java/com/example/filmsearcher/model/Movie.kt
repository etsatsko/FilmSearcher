package com.example.filmsearcher.model
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie (

    @ColumnInfo(name = "title")
    var title: String = "",

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "poster_path")
    @Nullable
    var poster_path: String = "",

    @Ignore
    @SerializedName("result")
    val movieList: List<Movie> = listOf()

)