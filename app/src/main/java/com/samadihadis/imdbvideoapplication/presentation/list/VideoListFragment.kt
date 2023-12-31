package com.samadihadis.imdbvideoapplication.presentation.list

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.samadihadis.imdbvideoapplication.data.API
import com.samadihadis.imdbvideoapplication.data.MovieModel
import com.samadihadis.imdbvideoapplication.data.PopularMovieModel
import com.samadihadis.imdbvideoapplication.databinding.FragmentVideoListBinding
import com.samadihadis.imdbvideoapplication.util.visible
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private var movieList = listOf<MovieModel>()
    private var videoAdaptor: VideoAdaptor? = null
    private var animation: ObjectAnimator? = null
    private var doubleBackToExitPressedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cleanList()
        initLoadingAnimator()
        initialRecycleView()
        getData()
        onBackPressedCallback()
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

    private fun setupAdapter(movieList: List<PopularMovieModel>) {
        videoAdaptor = VideoAdaptor(this.movieList, findNavController())
        binding.recyclerViewVideo.adapter = videoAdaptor
    }

    private fun cleanList() {
        movieList = listOf()
        videoAdaptor?.notifyDataSetChanged()
    }


    private fun getData() {
        binding.progressBarLoading.visible()
        animation?.start()

//"https://api.themoviedb.org/3/movie/popular?api_key=8f2b52cb3578ed865acfbd4d642dc062"
        lifecycleScope.launch {
            val response = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(API::class.java)
                .getMovie("8f2b52cb3578ed865acfbd4d642dc062")
            onResponse(response)
        }
    }

    private fun onResponse(response: retrofit2.Response<PopularMovieModel>) {
        if (response != null && response.isSuccessful()) {

        } else {
            Log.d("tagX", ": response failed")
        }
    }

    private fun getDataAndShowThem(rawData: String): PopularMovieModel {
        val gson = Gson()
        val obj: PopularMovieModel = gson.fromJson(rawData, PopularMovieModel::class.java)
        return obj
    }

    private fun initLoadingAnimator() {
        animation = ObjectAnimator.ofFloat(binding.progressBarLoading, "rotation", 0f, 360f)
        animation?.duration = 1000
        animation?.repeatCount = ObjectAnimator.INFINITE
        animation?.interpolator = LinearInterpolator()
    }

    private fun onBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (doubleBackToExitPressedOnce) {
                        requireActivity().finish()
                        return
                    }
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(requireContext(), "click back button again", Toast.LENGTH_SHORT)
                        .show()
                    Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
                }
            })
    }
}