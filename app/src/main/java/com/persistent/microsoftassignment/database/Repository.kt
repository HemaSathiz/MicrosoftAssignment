package com.persistent.microsoftassignment.database

import androidx.lifecycle.MutableLiveData
import com.persistent.microsoftassignment.api.RestInterface
import com.persistent.microsoftassignment.models.Movies
import com.persistent.microsoftassignment.models.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class Repository@Inject constructor(
    private val moviesDao: MoviesDao,
    private val apiService: RestInterface
) {
    private val data: MutableLiveData<Movies> = MutableLiveData<Movies>()

    fun getVideoDetails(): MutableLiveData<Movies> {
        CompositeDisposable().add(
            apiService!!.getVideoDetails()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        data.setValue(result);
                    },

                    { error ->


                    })

        )
        return data
    }


    fun insertMovieData(moviesDetails: List<Result>): io.reactivex.Observable<String> {
        return Observable.create { subscriber ->
            try {
                moviesDao.insertMovies(moviesDetails)
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
                moviesDao.deleteMovieDetails()

                subscriber.onNext("success")
            } catch (e: Exception) {
                subscriber.onError(e)
                e.printStackTrace()
            }
        }
    }

    fun getMovieDetails(): io.reactivex.Observable<List<Result>> {
        return Observable.create { subscriber ->
            try {
                var movieDetails: List<Result>? =moviesDao.getMovieDetails()
                subscriber.onNext(movieDetails!!)

                subscriber.onComplete()
            } catch (e: Exception) {
                subscriber.onError(e)
                e.printStackTrace()
            }
        }
    }
}