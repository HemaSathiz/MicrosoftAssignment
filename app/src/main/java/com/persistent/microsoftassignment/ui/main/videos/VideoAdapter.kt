package com.persistent.microsoftassignment.ui.main.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.persistent.microsoftassignment.R
import com.persistent.microsoftassignment.databinding.ItemVideoBinding
import com.persistent.microsoftassignment.models.Result
import javax.inject.Inject
import javax.inject.Singleton


class VideoAdapter @Inject constructor()  : PagedListAdapter<Result, RecyclerView.ViewHolder>(UserDiffCallback) {

     val videoDetailsList: ArrayList<Result> = ArrayList()

    override fun getItemCount() = videoDetailsList.size

    override fun onCreateViewHolder(parent : ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val binding: ItemVideoBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_video, parent, false
        ) as ItemVideoBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, p1: Int) {

        (holder as ViewHolder).bind( getItem(p1)!!)
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

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }




}