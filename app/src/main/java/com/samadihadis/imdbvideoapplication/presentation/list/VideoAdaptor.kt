package com.samadihadis.imdbvideoapplication.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.imdbvideoapplication.R
import com.samadihadis.imdbvideoapplication.data.VideoModel

class VideoAdaptor(
    private var videoList: List<VideoModel>,
    private val navController: NavController,
    ) : RecyclerView.Adapter<VideoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = videoList[position].title
            descriptionTextView.text = videoList[position].description
            Glide.with(holder.rootLayout.context)
                .load(videoList[position].bannerImageLink)
                .error(R.drawable.icon_video)
                .transform(CenterCrop(), RoundedCorners(40))
                .into(avatarImageView)
            favoriteImageView.setOnClickListener {

            }
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
