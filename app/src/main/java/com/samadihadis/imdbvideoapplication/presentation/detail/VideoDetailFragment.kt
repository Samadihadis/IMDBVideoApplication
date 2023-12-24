package com.samadihadis.imdbvideoapplication.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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
            toolbar.title = args.videoModel.title
            filmDescriptionId.text = args.videoModel.description
            filmAuthorId.text = args.videoModel.author
            filmBuildYearId.text = args.videoModel.buildYear.toString()
            if (args.videoModel.isFavorite) {
                favoriteImageView.setImageResource(R.drawable.icon_fav_fill)
            } else {
                favoriteImageView.setImageResource(R.drawable.icon_fav_empty)
            }
        }
    }

}