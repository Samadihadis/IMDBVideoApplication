package com.samadihadis.imdbvideoapplication.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.samadihadis.imdbvideoapplication.R
import com.samadihadis.imdbvideoapplication.databinding.FragmentVideoDetailBinding

class VideoDetailFragment : Fragment() {

    private val args by navArgs<VideoDetailFragmentArgs>()

    private lateinit var binding: FragmentVideoDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.title = args.movieModel.title
            descriptionValueTextView.text = args.movieModel.overview
            yearValueTextView.text = args.movieModel.releaseDate
            adultValueTextView.text = args.movieModel.adult.toString()
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/w500" +args.movieModel.backdropPath)
                .placeholder(R.drawable.banner_image_placeholder)
                .into(bannerImageView)
            progressBarAverage.progress = args.movieModel.voteAverage.toInt()
        }
    }

}