package com.persistent.microsoftassignment.helper

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

object NetworkHelper {

    fun haveNetworkConnection(activity: Activity): Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

}