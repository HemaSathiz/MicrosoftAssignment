package com.persistent.microsoftassignment.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(
    @PrimaryKey
    @NonNull var id: Int?= 0,
    var adult: Boolean?= false,
    var backdrop_path: String?= null,
    var original_language: String?= null,
    var original_title: String?= null,
    var overview: String?= null,
    var popularity: Double?= 0.0,
    var poster_path: String?= null,
    var release_date: String?= null,
    var title: String?= null,
    var video: Boolean?= false,
    var vote_average: Float?= 0.0f,
    var vote_count: Int?= 0
)