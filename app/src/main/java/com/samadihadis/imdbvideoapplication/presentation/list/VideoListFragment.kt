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
import com.samadihadis.imdbvideoapplication.data.VideoModel
import com.samadihadis.imdbvideoapplication.databinding.FragmentVideoListBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private var videoList = mutableListOf<VideoModel>()
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
        // https://github.com/Samadihadis/WeatherApplication/blob/master/app/src/main/java/com/samadihadis/weatherapplication/MainActivity.kt

    }

    private fun initialRecycleView() {
        val recycleView = binding.recyclerViewVideo

        recycleView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recycleView.addItemDecoration(dividerItemDecoration)

        videoAdaptor = VideoAdaptor(videoList, findNavController())
        recycleView.adapter = videoAdaptor
    }
    private fun cleanList() {
        videoList.clear()
        videoAdaptor?.notifyDataSetChanged()
    }

}