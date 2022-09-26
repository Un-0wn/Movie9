package com.example.main9.ui.fragment.home_.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.main9.ends.EndsResponse
import com.example.main9.ui.repository.Repository
import com.example.main9.utils.performGetOperation
import com.example.main9.utils.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val trendingMovies: LiveData<Resource<EndsResponse>> by lazy {
        repository.getTrending()
    }

}