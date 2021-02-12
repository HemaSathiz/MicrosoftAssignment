package com.persistent.microsoftassignment.ui.main.videos

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.persistent.microsoftassignment.R
import com.persistent.microsoftassignment.databinding.FragmentVideosBinding
import com.persistent.microsoftassignment.models.Movies
import com.persistent.microsoftassignment.models.Result
import kotlinx.android.synthetic.main.fragment_videos.*

class VideosFragment : Fragment() {

    companion object {
        fun newInstance() = VideosFragment()
    }

    private lateinit var binding: FragmentVideosBinding
    private lateinit var viewModel: VideoViewModel
    private var viewManager: GridLayoutManager? = null
    private var viewAdapter: VideoAdapter? = null
    private  var videoDetails: List<Result> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.swipeRefresh.setColorSchemeColors(Color.GRAY, Color.GRAY, Color.GRAY)

        viewManager = GridLayoutManager(activity!!,2)
        viewAdapter = VideoAdapter()
        binding.movieRecyclerView.setHasFixedSize(true)
        binding.movieRecyclerView.layoutManager = viewManager
        binding.movieRecyclerView.adapter = viewAdapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchVideoDetails(activity!!)
        }
        binding.progressBar.visibility = View.VISIBLE

        viewModel.getVideoDetails().observe(this, androidx.lifecycle.Observer<Movies> { response ->
            if(response != null) {
                lin_fragment_video_linear_no_network_connection.visibility = View.GONE
                movieRecyclerView.visibility = View.VISIBLE
                videoDetails = response.results
                progressBar.visibility = View.GONE
                if (swipeRefresh.isRefreshing) {
                    swipeRefresh.isRefreshing = false
                }
                viewModel.addVideoItemsToList(response.results)
            }

        })

        viewModel.fetchVideoDetails(activity!!)

        viewModel.getErrorResponseVideoDetails().observe(this, androidx.lifecycle.Observer<String> { response ->
            if (swipeRefresh.isRefreshing) {
                swipeRefresh.isRefreshing = false
            }
            progressBar.visibility = View.GONE
            lin_fragment_video_linear_no_network_connection.visibility = View.VISIBLE
            movieRecyclerView.visibility = View.GONE

        })

    }

}