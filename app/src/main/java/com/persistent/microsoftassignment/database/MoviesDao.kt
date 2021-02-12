package com.persistent.microsoftassignment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movieList: Movies)

    @Query("SELECT * FROM Movies")
    fun getMovieDetails(): Movies

    @Query("DELETE FROM Movies")
    fun deleteMovieDetails()
}