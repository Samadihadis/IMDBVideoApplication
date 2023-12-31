package com.samadihadis.imdbvideoapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
        @GET("popular")
        suspend fun getMovie(
            @Query("apiKey") apiKey : String
        ): Response<PopularMovieModel>
}