package com.persistent.microsoftassignment.database

import android.content.Context
import com.persistent.microsoftassignment.AppController
import io.reactivex.Observable

class DatabaseRepository(context : Context) {

    fun insertMovieData(applyIncidenceRequestModel: Movies): io.reactivex.Observable<String> {
        return Observable.create { subscriber ->
            try {
                AppController.INSTANCE?.getDB()?.movieDao()?.insertMovies(applyIncidenceRequestModel)
                subscriber.onNext("success")
            } catch (e: Exception) {
                subscriber.onError(e)
                e.printStackTrace()
            }
        }
    }

    fun deleteMovieDetails(): io.reactivex.Observable<String> {
        return Observable.create { subscriber ->
            try {
                AppController.INSTANCE?.getDB()?.movieDao()?.deleteMovieDetails()

                subscriber.onNext("success")
            } catch (e: Exception) {
                subscriber.onError(e)
                e.printStackTrace()
            }
        }
    }

    fun getMovieDetails(): io.reactivex.Observable<Movies> {
        return Observable.create { subscriber ->
            try {
                var movieDetails: Movies? =AppController.INSTANCE?.getDB()?.movieDao()?.getMovieDetails()
                subscriber.onNext(movieDetails!!)
                subscriber.onComplete()
            } catch (e: Exception) {
                subscriber.onError(e)
                e.printStackTrace()
            }
        }
    }
}