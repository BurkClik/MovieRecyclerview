package com.example.movierecyclerview.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecyclerview.data.remote.Movie
import com.example.movierecyclerview.databinding.ItemPopularMoviesBinding
import com.example.movierecyclerview.ui.movie.list.PopularMovieAdapter.PopularMovieViewHolder
import com.example.movierecyclerview.util.Constants

class PopularMovieAdapter : ListAdapter<Movie, PopularMovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding =
            ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PopularMovieViewHolder(private val binding: ItemPopularMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textViewMovieTitle.text = movie.title
            Glide.with(binding.root.context)
                .load("${Constants.IMAGE_BASE_URL}${movie.poster_path}")
                .centerCrop()
                .into(binding.imageViewMoviePoster)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }
}