package com.example.filmsearcher.manager

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsearcher.model.Movie
import com.example.filmsearcher.view.MovieAdapter

class UiHelper {
    companion object {
        fun updateAdapter(view: View, rv: RecyclerView, movies: List<Movie>?) {
            val adapter = MovieAdapter()

            rv.layoutManager = GridLayoutManager(view.context, 2)
            rv.adapter = adapter

            if (movies != null) {
                adapter.update(movies)
            }
        }
    }
}