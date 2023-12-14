package com.samadihadis.imdbvideoapplication.presentation.list

import android.net.Uri

data class Video(

    val imageVideo: Uri,
    val title: String,
    val description: String,
    val isFavorite: Boolean ,
    val goDetail : Boolean
)
