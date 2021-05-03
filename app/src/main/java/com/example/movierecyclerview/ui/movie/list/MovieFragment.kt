package com.example.movierecyclerview.ui.movie.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movierecyclerview.R
import com.example.movierecyclerview.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val movieAdapter = MovieAdapter()

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewMovie.apply {
            adapter = movieAdapter
            addItemDecoration(MovieDecoration())
            //setHasFixedSize(true)
        }

        viewModel.response.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }

        movieAdapter.itemClickListener = {
            val action = MovieFragmentDirections.movieAction(
                movieTitle = it.original_title,
                movieImage = it.poster_path,
                movieOverview = it.overview,
            )
            findNavController().navigate(action)
        }

        Log.i("Burak", "Fragment onViewCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}