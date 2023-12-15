package com.samadihadis.imdbvideoapplication.presentation.list

import android.net.Uri

data class VideoModel(

    val bannerImageLink: String,
    val title: String,
    val description: String,
    val isFavorite: Boolean ,
    val author : String ,
    val buildYear : Int ,
    val videoLink : String
)
