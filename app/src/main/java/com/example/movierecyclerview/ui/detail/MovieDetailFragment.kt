package com.example.movierecyclerview.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movierecyclerview.databinding.FragmentMovieDetailBinding
import com.example.movierecyclerview.util.Constants

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieTitle = args.movieTitle
        val moviePoster = args.movieImage
        val movieOverview = args.movieOverview

        binding.apply {
            textViewMovieDetailTitle.text = movieTitle
            textViewMovieDetailOverview.text = movieOverview
        }

        Glide.with(view)
            .load("${Constants.IMAGE_BASE_URL}${moviePoster}")
            .centerCrop()
            .into(binding.imageViewMovieDetailPoster)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}