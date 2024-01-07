package com.samadihadis.imdbvideoapplication.presentation.list

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.samadihadis.imdbvideoapplication.R

class VideoItemGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val avatarImageView: AppCompatImageView
    val titleTextView: MaterialTextView
    val buildYearTextView: MaterialTextView
    val rootLayout: ConstraintLayout

    init {
        view.apply {
            avatarImageView = findViewById(R.id.videoImageView)
            titleTextView = findViewById(R.id.titleTextView)
            buildYearTextView = findViewById(R.id.buildYearTextView)
            rootLayout = findViewById(R.id.gridRootLayout)
        }
    }
}