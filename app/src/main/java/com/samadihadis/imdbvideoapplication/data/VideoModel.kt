package com.samadihadis.imdbvideoapplication.data

import java.io.Serializable

data class VideoModel(
    val bannerImageLink: String,
    val title: String,
    val description: String,
    val isFavorite: Boolean ,
    val author : String ,
    val buildYear : Int ,
    val videoLink : String
): Serializable
