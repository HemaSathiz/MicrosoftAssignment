package com.persistent.microsoftassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.persistent.microsoftassignment.models.Result

@Database(entities = [Result::class],
    version = 1, exportSchema = true)
abstract class AssignmentDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao

}