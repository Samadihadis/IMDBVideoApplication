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
    private val navController: NavController,
) : RecyclerView.Adapter<VideoItemGridViewHolder>() {

    private var videoList: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemGridViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video_grid, parent, false)
        return VideoItemGridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun addItem(movieModel: MovieModel) {
        videoList.add(movieModel)
        notifyItemInserted(videoList.size - 1)
    }

    fun addItemList(movieModelList: List<MovieModel>) {
        videoList.addAll(movieModelList)
        notifyItemRangeInserted(videoList.size - 1, movieModelList.size)
    }

    override fun onBindViewHolder(holder: VideoItemGridViewHolder, position: Int) {
        holder.apply {
            titleTextView.text = videoList[position].title
            buildYearTextView.text = videoList[position].releaseDate
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