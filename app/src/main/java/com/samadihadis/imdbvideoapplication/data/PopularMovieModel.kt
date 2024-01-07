package com.samadihadis.imdbvideoapplication.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PopularMovieModel(
    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<MovieModel>? = null,
    @SerializedName("total_pages") val totalPages : Int,
    @SerializedName("total_results") val totalResults : Int
): Serializable
