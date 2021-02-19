package com.persistent.microsoftassignment.ui.main.videos

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.persistent.microsoftassignment.models.Result
import java.text.SimpleDateFormat
import java.util.*

class ItemVideoDetailsListViewModel(row: Result) : BaseObservable() {

    var title: ObservableField<String> = ObservableField()
    var description: ObservableField<String> = ObservableField()
    var imageUrl: ObservableField<String> = ObservableField()
    var year: ObservableField<String> = ObservableField()
    var raringBar: ObservableField<String> = ObservableField()
    var vote: ObservableField<String> = ObservableField()

    init {
        title.set(row.title)
        description.set(row.overview)
        year.set(getYearFromDate(row.release_date!!))
        imageUrl.set(row.poster_path)
        raringBar.set(row.vote_average.toString())
        vote.set(row.vote_count.toString())

    }

    private fun getYearFromDate(dateString: String): String {
        val fmt = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = fmt.parse(dateString)
        val fmtOut = SimpleDateFormat("yyyy")
        return fmtOut.format(date)
    }
}