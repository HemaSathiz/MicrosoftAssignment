package com.persistent.microsoftassignment.database

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.persistent.microsoftassignment.models.Result

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieList: List<Result>)

    @Query("SELECT * FROM Result")
    fun getMovieDetails(): List<Result>

    @Query("DELETE FROM Result")
    fun deleteMovieDetails()


}