package com.samadihadis.imdbvideoapplication.presentation.list

import android.os.Bundle
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
        prepareData()
        initialRecycleView()
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

    private fun prepareData() {
        videoList.add(
            VideoModel(
                title = "title1",
                description = "description1",
                bannerImageLink = "link1",
                author = "author1",
                buildYear = 2020,
                videoLink = "link2",
                isFavorite = false
            )
        )
        videoList.add(
            VideoModel(
                title = "title2",
                description = "description2",
                bannerImageLink = "link1",
                author = "author2",
                buildYear = 2023,
                videoLink = "link2",
                isFavorite = true
            )
        )
    }

    private fun cleanList() {
        videoList.clear()
        videoAdaptor?.notifyDataSetChanged()
    }

}