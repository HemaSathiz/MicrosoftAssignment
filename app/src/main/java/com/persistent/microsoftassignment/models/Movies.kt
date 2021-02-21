package com.persistent.microsoftassignment.models

data class Movies(
    val page: Int,
    val results: MutableList<Result>,
    val total_pages: Int,
    val total_results: Int
)