package com.example.movierecyclerview.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String) : Call<MovieResponse>

    @GET("/3/genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String) : Call<GenreResponse>
}