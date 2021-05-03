package com.example.movierecyclerview.ui.movie.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movierecyclerview.data.remote.GenreResponse
import com.example.movierecyclerview.data.remote.Movie
import com.example.movierecyclerview.data.remote.MovieClient
import com.example.movierecyclerview.data.remote.MovieResponse
import com.example.movierecyclerview.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val _response = MutableLiveData<List<Movie>>()
    val response: LiveData<List<Movie>> get() = _response

    init {
        Log.i("Burak", "ViewModel created!")
        getMoviesFromApi()
    }

    private fun getGenres() {
        MovieClient.retrofitService.getGenres(Constants.API)
            .enqueue(object : Callback<GenreResponse> {
                override fun onResponse(
                    call: Call<GenreResponse>,
                    response: Response<GenreResponse>
                ) {
                    Log.i("Burak", "${response.body()!!.genres}")
                }

                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                    Log.e("Burak", t.message.toString())
                }
            })
    }

    private fun getMoviesFromApi() {
        MovieClient.retrofitService.getPopularMovies(Constants.API)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    Log.i("Burak", "{${response.body()}}")
                    if (response.isSuccessful && response.body() != null) {
                        _response.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Burak", t.message.toString())
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Burak", "ViewModel destroyed!")
    }

}