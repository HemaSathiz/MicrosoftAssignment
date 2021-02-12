package com.persistent.microsoftassignment.ui.main.videos

import android.app.Activity
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.persistent.microsoftassignment.api.AppUtils
import com.persistent.microsoftassignment.database.DatabaseRepository
import com.persistent.microsoftassignment.helper.ConstantHelper
import com.persistent.microsoftassignment.helper.NetworkHelper
import com.persistent.microsoftassignment.models.Movies
import com.persistent.microsoftassignment.models.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


class VideoViewModel : ViewModel(){

    private var videoData = MutableLiveData<Movies>()
    var videoArrayList: ObservableList<Result> = ObservableArrayList()
    private var errorDetails = MutableLiveData<String>()
    private var databaseRespository: DatabaseRepository? = null


    fun fetchVideoDetails(activity: Activity, databaseRepository: DatabaseRepository) {
        val repository = AppUtils.getSOService()
        if (NetworkHelper.haveNetworkConnection(activity)) {
            CompositeDisposable().add(
                repository.getVideoDetails()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { result ->
                            var movieDB = com.persistent.microsoftassignment.database.Movies()
                            movieDB.jsonbody = Gson().toJson(result)
                            deleteInsertMovieDetails(databaseRepository!!, movieDB)
                            videoData.postValue(result)

                        },
                        { error ->
                            loadError(error)
                        })
            )
        }else{
            getMovieDetails(databaseRepository)
        }
    }

    fun deleteInsertMovieDetails(
        databaseRepository: DatabaseRepository,
        movieDB: com.persistent.microsoftassignment.database.Movies
    ){
        CompositeDisposable().add(
            databaseRepository!!.deleteMovieDetails()
                .subscribeOn(Schedulers.computation())
                .flatMap { i -> databaseRepository.insertMovieData(movieDB) }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->


                    },

                    { error ->


                    })
        )
    }

    fun getMovieDetails(
        databaseRepository: DatabaseRepository
    ){
        CompositeDisposable().add(
            databaseRepository!!.getMovieDetails()
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    { result ->
                        if (result.jsonbody != null) {
                            val convertedObject = Gson().fromJson(
                                result.jsonbody,
                                Movies::class.java
                            )
                            videoData.postValue(convertedObject)
                        } else {
                            errorDetails.postValue(ConstantHelper.ERROR)

                        }
                    },

                    { error ->
                        errorDetails.postValue(ConstantHelper.ERROR)
                    })
        )
    }

    private fun loadError(error: Throwable) {
        when (error) {
            is HttpException -> {
                val responseBody = error.response()?.errorBody()
                responseBody?.let {
                    errorDetails.postValue(ConstantHelper.ERROR)
                }
            }
            is SocketTimeoutException -> {
                errorDetails.postValue(ConstantHelper.ERROR)
            }
            is IOException -> {
                errorDetails.postValue(ConstantHelper.ERROR)
            }
            else -> {
                error.message?.let {
                    errorDetails.postValue(ConstantHelper.ERROR)
                }
            }
        }
    }

    fun getVideoDetails(): MutableLiveData<Movies> {
        return videoData
    }

    fun getErrorResponseVideoDetails(): MutableLiveData<String> {
        return errorDetails
    }

    fun addVideoItemsToList(rows: List<Result>) {
        videoArrayList.clear()
        videoArrayList.addAll(rows)
    }



}