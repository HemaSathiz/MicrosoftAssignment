package com.persistent.microsoftassignment.ui.main.videos

import android.app.Activity
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.persistent.microsoftassignment.database.Repository
import com.persistent.microsoftassignment.helper.ConstantHelper
import com.persistent.microsoftassignment.helper.NetworkHelper
import com.persistent.microsoftassignment.models.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.Executor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    var videoData = MutableLiveData<PagedList<Result>>()
    var videoArrayList: ObservableList<Result> = ObservableArrayList()
    private var errorDetails = MutableLiveData<String>()


    fun fetchVideoDetails(activity: Activity?) {
        if (NetworkHelper.haveNetworkConnection(activity!!)) {
            CompositeDisposable().add(
                Observable.just( repository.getVideoDetails())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { result ->
                            deleteInsertMovieDetails(result)
                        },
                        { error ->
                            loadError(error)
                        })
            )
        }else{
            getMovieDetails()
        }
    }

  fun deleteInsertMovieDetails(
      movies: MutableLiveData<List<Result>>
    ){
        CompositeDisposable().add(
                repository.deleteMovieDetails()
                .subscribeOn(Schedulers.computation())
                .flatMap { i -> repository.insertMovieData(movies.value!!) }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        getMovieDetails()
                    },

                    { error ->


                    })
        )
    }

    private fun getMovieDetails(
    ){
        CompositeDisposable().add(
            repository!!.getMovieDetails()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    { result ->
                        val config = PagedList.Config.Builder()
                                .setPageSize(5)
                                .setEnablePlaceholders(false)
                                .setInitialLoadSizeHint(5)
                                .build()

                        val pagedList = PagedList.Builder(ListDataSource(result),config)
                                .setNotifyExecutor (UiThreadExecutor ())
                                .setFetchExecutor (AsyncTask.THREAD_POOL_EXECUTOR)
                                .build ()
                        videoData!!.postValue(pagedList!!)
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

    fun getErrorResponseVideoDetails(): MutableLiveData<String> {
        return errorDetails
    }

    fun addVideoItemsToList(rows: List<Result>) {
        videoArrayList.clear()
        videoArrayList.addAll(rows)
    }

    inner class UiThreadExecutor: Executor {
        private val handler = Handler (Looper.getMainLooper ())
        override fun execute (command: Runnable) {
            handler.post (command)
        }
    }

   inner class ListDataSource (private val items: List<Result>): PageKeyedDataSource<Int, Result>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
            callback.onResult (items, 0, items.size)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        }
    }



}