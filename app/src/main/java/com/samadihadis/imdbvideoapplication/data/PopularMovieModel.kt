package com.samadihadis.imdbvideoapplication.data

import java.io.Serializable

data class PopularMovieModel(
    val page : Int ,
    val results : List<MovieModel>,
    val total_pages : Int,
    val total_results : Int
): Serializable
