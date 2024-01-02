package com.samadihadis.imdbvideoapplication.data

import com.samadihadis.imdbvideoapplication.util.RetrofitClient.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    fun getPopularMovie(@Query("api_key") apiKey: String = API_KEY): Call<PopularMovieModel>
}