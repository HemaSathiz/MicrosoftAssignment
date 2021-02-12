package com.persistent.microsoftassignment

import android.app.Application
import androidx.room.Room
import com.persistent.microsoftassignment.database.AssignmentDatabase

class AppController : Application() {

    companion object{
        var INSTANCE: AppController? = null
        val DATABASE_NAME = "Assignment"
        private var database: AssignmentDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AssignmentDatabase::class.java, DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        INSTANCE = this
    }


    fun getDB(): AssignmentDatabase? {
        return database
    }


}