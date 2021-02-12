package com.persistent.microsoftassignment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.persistent.microsoftassignment.R

import android.view.MenuItem
import com.persistent.microsoftassignment.ui.main.videos.VideosFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.clRootView, VideosFragment.newInstance())
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
