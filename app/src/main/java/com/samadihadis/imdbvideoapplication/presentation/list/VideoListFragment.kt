package com.samadihadis.imdbvideoapplication.presentation.list

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samadihadis.imdbvideoapplication.R
import com.samadihadis.imdbvideoapplication.data.MovieModel
import com.samadihadis.imdbvideoapplication.data.PopularMovieModel
import com.samadihadis.imdbvideoapplication.databinding.FragmentVideoListBinding
import com.samadihadis.imdbvideoapplication.util.RetrofitClient
import com.samadihadis.imdbvideoapplication.util.gone
import com.samadihadis.imdbvideoapplication.util.visible
import retrofit2.Call
import retrofit2.Response


class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentVideoListBinding
    private var movieList = listOf<MovieModel>()
    private val videoGridAdapter: VideoGridAdapter by lazy {
        VideoGridAdapter(findNavController())
    }
    private val videoListAdapter: VideoListAdapter by lazy {
        VideoListAdapter(findNavController())
    }
    private var animation: ObjectAnimator? = null
    private var doubleBackToExitPressedOnce = false
    private var isGrid: Boolean = true
    private val decoration: DividerItemDecoration by lazy {
        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
    }

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
        setupView()
        getData()
        onBackPressedCallback()
    }

    private fun changeFabState() = with(binding) {
        if (!isGrid) {
            fabButton.setImageResource(R.drawable.baseline_format_list)
            isGrid = true
        } else {
            fabButton.setImageResource(R.drawable.baseline_grid_view)
            isGrid = false
        }
    }


    private fun setupListAdapter() {
        with(binding.videoRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(decoration)
            adapter = videoListAdapter
        }
        videoListAdapter.addItemList(movieList)
    }

    private fun setupGridAdapter() {
        with(binding.videoRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            removeItemDecoration(decoration)
            adapter = videoGridAdapter
        }
        videoGridAdapter.addItemList(movieList)
    }

    private fun changeAdapter() {
        if (isGrid) {
            setupGridAdapter()
        } else {
            setupListAdapter()
        }
    }

    private fun setupView() = with(binding) {
        fabButton.setOnClickListener {
            changeFabState()
            changeAdapter()
        }
    }

    private fun cleanList() {
        movieList = listOf()
        videoListAdapter?.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.progressBarLoading.visible()
        animation?.start()
    }

    private fun hideLoading() {
        animation?.cancel()
        binding.progressBarLoading.gone()
    }

    private fun getData() {
        showLoading()
        RetrofitClient.apiService.getPopularMovie()
            .enqueue(object : retrofit2.Callback<PopularMovieModel> {
                override fun onResponse(
                    call: Call<PopularMovieModel>,
                    response: Response<PopularMovieModel>
                ) {
                    hideLoading()
                    onServerResponse(response)
                    changeAdapter()
                }

                override fun onFailure(call: Call<PopularMovieModel>, t: Throwable) {
                    hideLoading()
                    Toast.makeText(requireContext(), "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    private fun onServerResponse(response: Response<PopularMovieModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.results.isNullOrEmpty()) {
                movieList = response.body()?.results!!
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
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