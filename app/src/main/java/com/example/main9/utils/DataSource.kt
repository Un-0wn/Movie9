package com.example.main9.utils

import androidx.lifecycle.LiveData
import com.example.main9.ends.EndsResponse
import com.example.main9.ends.TVResponse

import com.example.main9.utils.vo.Resource

interface DataSource {
    fun getMovies(category: String): LiveData<Resource<EndsResponse>>
    fun getTrending(): LiveData<Resource<EndsResponse>>

    fun getTv(category: String): LiveData<Resource<TVResponse>>
    fun kids(category: String): LiveData<Resource<TVResponse>>

}