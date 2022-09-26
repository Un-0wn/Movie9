package com.example.main9.ui.repository

import com.example.main9.ends.Ends
import com.example.main9.ends.EndsResponse
import com.example.main9.ends.TVResponse
import com.example.main9.utils.vo.Resource
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val ends: Ends
) : ResponseHelper() {

    suspend fun getMovies(category: String): Resource<EndsResponse> = getResponseResult {
        ends.getMovieResponse(category)
    }

    suspend fun getTrending(): Resource<EndsResponse> = getResponseResult {
        ends.getTrending()
    }

    suspend fun getTv(category: String): Resource<TVResponse> = getResponseResult {
        ends.getSeries(category)
    }

}

