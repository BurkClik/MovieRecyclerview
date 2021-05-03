package com.example.movierecyclerview.data.remote

import com.example.movierecyclerview.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MovieClient {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}