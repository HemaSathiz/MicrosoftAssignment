package com.persistent.microsoftassignment


import com.persistent.microsoftassignment.models.Result
import java.util.ArrayList

object TestUtil {

    var listMovies :ArrayList<Result> = ArrayList()

    fun createMovies(): ArrayList<Result> {
        var resultObject = Result(
            123456, false, "name",
            "name", "name",
            "name", 0.0, "name",
            "2016", "Name", false, 1.2f, 2
        )
         listMovies.add(resultObject)
        return listMovies

    }


}