package com.persistent.microsoftassignment.ui.main.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.persistent.microsoftassignment.AppController
import com.persistent.microsoftassignment.R
import com.persistent.microsoftassignment.databinding.FragmentVideosBinding
import com.persistent.microsoftassignment.models.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment : Fragment() {

    companion object {
        fun newInstance() = VideosFragment()
    }

    private lateinit var binding: FragmentVideosBinding
     private val viewModel: VideoViewModel by viewModels()
    private var viewManager: LinearLayoutManager? = null
    private var viewAdapter: VideoAdapter? = null
    private  var videoDetails: List<Result> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewAdapter = VideoAdapter()
        binding.movieRecyclerView.setHasFixedSize(true)
        binding.movieRecyclerView.layoutManager = viewManager
        binding.movieRecyclerView.adapter = viewAdapter

        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchVideoDetails(activity)

        viewModel.videoData.observe(viewLifecycleOwner, { response ->
            if (response != null) {
                binding.linFragmentVideoLinearNoNetworkConnection.visibility = View.GONE
                binding.movieRecyclerView.visibility = View.VISIBLE
                videoDetails = response
                binding.progressBar.visibility = View.GONE
                viewModel.addVideoItemsToList(response)
               // viewAdapter!!.submitList(response)
            }

        })


        viewModel.getErrorResponseVideoDetails().observe(viewLifecycleOwner, { response ->

            binding.progressBar.visibility = View.GONE
            binding.linFragmentVideoLinearNoNetworkConnection.visibility = View.VISIBLE
            binding.movieRecyclerView.visibility = View.GONE

        })

    }

}