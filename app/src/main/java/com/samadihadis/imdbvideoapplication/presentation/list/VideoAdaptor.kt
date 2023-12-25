package com.samadihadis.imdbvideoapplication.presentation.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.samadihadis.imdbvideoapplication.R
import com.samadihadis.imdbvideoapplication.data.VideoModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

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
            favoriteImageView.setImageResource(
                if (videoList[position].isFavorite) {
                    R.drawable.icon_fav_fill
                } else {
                    R.drawable.icon_fav_empty
                }
            )
            favoriteImageView.setOnClickListener {
                if (videoList[position].isFavorite) {
                    videoList[position].isFavorite = false
                    favoriteImageView.setImageResource(R.drawable.icon_fav_empty)
                } else {
                    videoList[position].isFavorite = true
                    favoriteImageView.setImageResource(R.drawable.icon_fav_fill)
                }
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

    private fun getData(){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/popular?api_key=8f2b52cb3578ed865acfbd4d642dc062")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d("tagx", "onFailure:failed")
            }

            override fun onResponse(call: Call, response: Response) {
                val rawContent = response.body!!.string()
                getDataAndShowThem(rawContent)
            }
        })
    }

    fun getDataAndShowThem(rawData : String){
        val jsonObject = JSONObject(rawData)

        val videoArray = jsonObject.getJSONArray("results")
        val videoObject = videoArray.getJSONObject(0)
        val posterPath = videoObject.getString("poster_path")
        val imageUrl = "http://image.tmdb.org/t/p/${posterPath}.jpg"

        val title = jsonObject.getJSONObject("results").getString("original_title")
        val description = jsonObject.getJSONObject("results").getString("overview")
        val buildYear = jsonObject.getJSONObject("results").getString("release_date")


    }

}
