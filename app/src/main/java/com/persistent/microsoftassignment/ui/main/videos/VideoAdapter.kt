package com.persistent.microsoftassignment.ui.main.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.persistent.microsoftassignment.R
import com.persistent.microsoftassignment.databinding.ItemVideoBinding
import com.persistent.microsoftassignment.models.Result

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private val videoDetailsList: ArrayList<Result> = ArrayList()

    override fun getItemCount() = videoDetailsList.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding: ItemVideoBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_video, parent, false
        ) as ItemVideoBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bind(videoDetailsList[p1])
    }

    fun addItems(arrayListUserGeographyDetails: List<Result>) {
        videoDetailsList.addAll(arrayListUserGeographyDetails)
        notifyDataSetChanged()
    }

    fun clearItems() {
        videoDetailsList.clear()
    }

    inner class ViewHolder(var itemListBinding: ItemVideoBinding) : RecyclerView.ViewHolder(itemListBinding.root) {

        private lateinit var mVideoItemViewModel: ItemVideoDetailsListViewModel

        fun bind(row: Result) {
            mVideoItemViewModel = ItemVideoDetailsListViewModel(row)
            itemListBinding.viewModel = mVideoItemViewModel
            itemListBinding.executePendingBindings()
        }
    }


}