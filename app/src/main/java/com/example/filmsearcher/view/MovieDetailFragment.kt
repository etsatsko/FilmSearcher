package com.example.filmsearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.filmsearcher.databinding.FragmentMovieDetailBinding
import com.example.filmsearcher.manager.ImageHelper
import com.example.filmsearcher.manager.MovieManager
import com.example.filmsearcher.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailFragment : Fragment() {
    lateinit var movie : Movie
    lateinit var movieManager : MovieManager
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        movieManager = MovieManager(requireContext())
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.movieTitle.text = movie.title
        binding.synopsis.text = movie.overview
        Glide
            .with(view)
            .load(ImageHelper.getImageUrl(movie.poster_path))
            .into(binding.movieImage)

        binding.buttonHeart.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    movieManager.markWatchedMovie(movie)
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}