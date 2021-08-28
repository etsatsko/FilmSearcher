package com.example.filmsearcher.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmsearcher.databinding.FragmentWatchedBinding
import com.example.filmsearcher.manager.MovieManager
import com.example.filmsearcher.manager.UiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WatchedFragment : Fragment() {
    private var _binding: FragmentWatchedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var movieManager: MovieManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchedBinding.inflate(inflater, container, false)
        val view = binding.root

        movieManager = MovieManager(requireContext())

        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieManager.getLikedMovies()
            withContext(Dispatchers.Main) {
                UiHelper.updateAdapter(view, binding.recView, movies)
            }
        }
        return view
    }
}