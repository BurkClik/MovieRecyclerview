package com.example.movierecyclerview.ui.movie.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecyclerview.data.remote.Movie
import com.example.movierecyclerview.databinding.ItemMovieBinding
import com.example.movierecyclerview.ui.movie.list.MovieAdapter.MovieViewHolder
import com.example.movierecyclerview.util.Constants

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Movie) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, itemClickListener = itemClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(private val binding: ItemMovieBinding, private val itemClickListener: (Movie) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.textViewMovieTitle.text = movie.original_title
            Glide.with(binding.root.context)
                .load("${Constants.IMAGE_BASE_URL}${movie.poster_path}")
                .centerCrop()
                .into(binding.imageViewMovie)

            binding.root.setOnClickListener {
                itemClickListener(movie)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }
}