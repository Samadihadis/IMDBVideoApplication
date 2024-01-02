package com.samadihadis.imdbvideoapplication.util

import com.samadihadis.imdbvideoapplication.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val API_KEY = "8f2b52cb3578ed865acfbd4d642dc062"
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().run {
        addInterceptor(logging)
        build()
    }
   private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client).build()

    val apiService : ApiService = retrofit.create(ApiService::class.java)
}