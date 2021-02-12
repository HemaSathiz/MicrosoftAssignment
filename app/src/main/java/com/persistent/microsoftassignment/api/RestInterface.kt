package com.persistent.microsoftassignment.api

import com.persistent.microsoftassignment.helper.ConstantHelper
import com.persistent.microsoftassignment.models.Movies
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestInterface {
    @Headers("Content-Type: application/json")
    @GET("3/movie/popular?api_key="+ ConstantHelper.API_KEY)
    fun getVideoDetails(): io.reactivex.Observable<Movies>
}