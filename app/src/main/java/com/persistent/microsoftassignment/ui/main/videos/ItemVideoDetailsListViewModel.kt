package com.persistent.microsoftassignment.ui.main.videos

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.persistent.microsoftassignment.models.Result

class ItemVideoDetailsListViewModel(row: Result) : BaseObservable() {

    var title: ObservableField<String> = ObservableField()
    private var type: ObservableField<String> = ObservableField()
    var imageUrl: ObservableField<String> = ObservableField()
    var year: ObservableField<String> = ObservableField()

    init {
        title.set(row.title)
        type.set(row.popularity.toString())
        year.set(row.release_date)
        imageUrl.set(row.poster_path)

    }
}