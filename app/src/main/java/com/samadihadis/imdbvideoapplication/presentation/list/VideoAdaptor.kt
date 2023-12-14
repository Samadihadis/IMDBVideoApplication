package com.samadihadis.imdbvideoapplication.presentation.list

import android.content.Context
import android.provider.MediaStore.Video
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.imdbvideoapplication.R

class VideoAdaptor(var videoList: List<VideoModel>, private var context: Context) :
    RecyclerView.Adapter<VideoAdaptor.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView
        val title: TextView
        val description: TextView
        val isFavorite: ImageView
        val goDetail: ImageView

        init {
            view.apply {
                image = findViewById(R.id.videoImageView)
                title = findViewById(R.id.titleTextView)
                description = findViewById(R.id.descriptionTextView)
                isFavorite = findViewById(R.id.isFavorite)
                goDetail = findViewById(R.id.goDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = videoList[position].title
            description.text = videoList[position].description
            Glide.with(context)
                .load(videoList[position].imageVideo)
                .error(R.drawable.video)
                .transform(CenterCrop(), RoundedCorners(40))
                .into(image)
        }
    }
}
