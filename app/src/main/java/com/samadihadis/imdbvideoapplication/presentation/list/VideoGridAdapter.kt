package com.samadihadis.imdbvideoapplication.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.imdbvideoapplication.R
import com.samadihadis.imdbvideoapplication.data.MovieModel

class VideoGridAdapter(
private var videoList: List<MovieModel>,
private val navController: NavController,
) : RecyclerView.Adapter<VideoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_list, parent, false)
        return VideoItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = videoList[position].title
            descriptionTextView.text = videoList[position].overview
            Glide.with(holder.rootLayout.context)
                .load("https://image.tmdb.org/t/p/w185" + videoList[position].backdropPath)
                .placeholder(R.drawable.banner_image_placeholder)
                .transform(CenterCrop(), RoundedCorners(40))
                .into(avatarImageView)
            rootLayout.setOnClickListener {
                navController.navigate(
                    VideoListFragmentDirections.actionToVideoDetailFragment(
                        videoList[position]
                    )
                )
            }
        }
    }
}