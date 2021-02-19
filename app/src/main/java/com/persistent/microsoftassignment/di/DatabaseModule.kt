package com.persistent.microsoftassignment.di

import android.app.Application
import androidx.room.Room
import com.persistent.microsoftassignment.database.AssignmentDatabase
import com.persistent.microsoftassignment.database.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    fun provideAssignmentDB(application: Application?): AssignmentDatabase {
        return Room.databaseBuilder(application!!, AssignmentDatabase::class.java, "Assignment")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideMoviesDao(movieDao : AssignmentDatabase): MoviesDao {
        return movieDao.movieDao()
    }
}