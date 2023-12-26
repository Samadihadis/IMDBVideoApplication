package com.samadihadis.imdbvideoapplication.presentation.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.samadihadis.imdbvideoapplication.R

class VideoItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val avatarImageView: ImageView
    val titleTextView: TextView
    val descriptionTextView: TextView
    val rootLayout: ConstraintLayout

    init {
        view.apply {
            avatarImageView = findViewById(R.id.videoImageView)
            titleTextView = findViewById(R.id.titleTextView)
            descriptionTextView = findViewById(R.id.descriptionTextView)
            rootLayout = findViewById(R.id.itemVideo)
        }
    }
}