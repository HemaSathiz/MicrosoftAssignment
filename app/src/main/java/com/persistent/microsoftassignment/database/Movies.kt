package com.persistent.microsoftassignment.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movies {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @NonNull
    var jsonbody: String? = null
}