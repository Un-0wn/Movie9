package com.example.main9.ends

import com.google.gson.annotations.Expose

data class EndsResponse(
    @Expose val page : Int,
    @Expose val results : List<Movie>,
    @Expose val total_results: Int,
    @Expose val total_pages: Int
)

data class SearchTrendingResponse(
    val results: List<SearchTrending>
)

data class TVResponse(
    @Expose val page : Int,
    @Expose val results : List<TV>
)

