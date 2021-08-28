package com.example.filmsearcher.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmsearcher.R
import com.example.filmsearcher.databinding.MovieItemBinding
import com.example.filmsearcher.manager.ImageHelper
import com.example.filmsearcher.manager.MovieManager
import com.example.filmsearcher.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieTitleHolder>() {
    lateinit var itemBinding: MovieItemBinding
    lateinit var movieManager : MovieManager

    var list = listOf<Movie>()
    lateinit var mContext : Context

    fun update(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTitleHolder {
        itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        movieManager = MovieManager(parent.context)
        mContext = parent.context

        return MovieTitleHolder(itemBinding)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: MovieTitleHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            fragmentJump(item)
        }
        val likeButton = itemBinding.heartCardButton
        likeButton.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    movieManager.markWatchedMovie(item)
                }
            }
        }
    }

    private fun fragmentJump(selectedMovie: Movie) {
        val newFragment = MovieDetailFragment()

        if (mContext is MainActivity) {
            val mainActivity = mContext as MainActivity
            mainActivity.movieSelected = selectedMovie
            mainActivity.switchContent(R.id.searchFragment, newFragment)
        }
    }

    class MovieTitleHolder(private val itemBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Movie) {
            val image_view = itemBinding.imageView

            Glide
                .with(itemBinding.root)
                .load(ImageHelper.getImageUrl(item.poster_path))
                .into(image_view)

            itemBinding.title.text = item.title
        }

    }
}