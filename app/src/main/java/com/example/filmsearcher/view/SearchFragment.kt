package com.example.filmsearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsearcher.databinding.FragmentSearchBinding
import com.example.filmsearcher.manager.MovieManager
import com.example.filmsearcher.manager.NetworkManager
import com.example.filmsearcher.manager.UiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    lateinit var movieManager: MovieManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        movieManager = MovieManager(requireContext())

        val view = binding.root

        binding.searchButton.setOnClickListener {
            val textField = binding.searchField
            val nm = NetworkManager(view.context)
            val connected = nm.isConnectedToInternet

            GlobalScope.launch(Dispatchers.IO) {
                val movieName = textField.text.toString()
                val movies = if (connected!!) {
                    movieManager.downloadMoviesByName(movieName)
                } else {
                    movieManager.getMoviesByName(movieName)
                }
                withContext(Dispatchers.Main) {
                    UiHelper.updateAdapter(view, binding.recView, movies)
                }
            }
        }

        return view
    }
}