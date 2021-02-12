package com.persistent.microsoftassignment.ui

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.persistent.microsoftassignment.R
import com.persistent.microsoftassignment.helper.ConstantHelper
import com.persistent.microsoftassignment.models.Result
import com.persistent.microsoftassignment.ui.main.videos.VideoAdapter

object BindingUtils {

    @JvmStatic
    @BindingAdapter("videoadapter")
    fun addVideoAdapter(recyclerView: RecyclerView, listPhotos: List<Result>?) {
        val adapter = recyclerView.adapter as VideoAdapter
        if (listPhotos != null && listPhotos.isNotEmpty()) {
            adapter?.clearItems()
            adapter?.addItems(listPhotos)
        }
    }
    @JvmStatic
    @BindingAdapter("app:imageResource")
    fun setImageUrl(imageView: ImageView, url: String?) {
        val context = imageView.context
        if (url != null) {
            Glide.with(context).load(ConstantHelper.VIDEO_URL+url).placeholder(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background))
                .into(imageView)
        }
    }
}