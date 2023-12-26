package com.samadihadis.imdbvideoapplication.presentation.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.samadihadis.imdbvideoapplication.data.MovieModel
import com.samadihadis.imdbvideoapplication.data.PopularMovieModel
import com.samadihadis.imdbvideoapplication.databinding.FragmentVideoListBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private var movieList = listOf<MovieModel>()
    private var videoAdaptor : VideoAdaptor? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cleanList()
        initialRecycleView()
        getData()
        // https://github.com/Samadihadis/WeatherApplication/blob/master/app/src/main/java/com/samadihadis/weatherapplication/MainActivity.kt
    }

    private fun initialRecycleView() {
        binding.recyclerViewVideo.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun setupAdapter() {
        videoAdaptor = VideoAdaptor(movieList, findNavController())
        binding.recyclerViewVideo.adapter = videoAdaptor
    }

    private fun cleanList() {
        movieList = listOf()
        videoAdaptor?.notifyDataSetChanged()
    }


    private fun getData() {
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
                val result = getDataAndShowThem(rawContent)
                movieList = result.results
                requireActivity().runOnUiThread {
                    setupAdapter()
                }
            }
        })
    }

    private fun getDataAndShowThem(rawData: String): PopularMovieModel {
        val gson = Gson()
        val obj : PopularMovieModel = gson.fromJson(rawData, PopularMovieModel::class.java)
        return obj
    }

}