package com.persistent.microsoftassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movies::class],
    version = 1, exportSchema = true)
abstract class AssignmentDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao

}