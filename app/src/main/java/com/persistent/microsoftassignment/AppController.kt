package com.persistent.microsoftassignment

import android.app.Application
import androidx.room.Room
import com.persistent.microsoftassignment.database.AssignmentDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController : Application() {}