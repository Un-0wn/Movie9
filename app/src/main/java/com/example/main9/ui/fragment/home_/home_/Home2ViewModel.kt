package com.example.main9.ui.fragment.home_.home_

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.main9.ends.EndsResponse
import com.example.main9.ui.repository.Repository
import com.example.main9.utils.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val trendingMovies: LiveData<Resource<EndsResponse>> by lazy {
        repository.getMovies("now_playing")
    }

    val upcomingMovies: LiveData<Resource<EndsResponse>> by lazy {
        repository.getMovies("upcoming")
    }


    val popularMovies: LiveData<Resource<EndsResponse>> by lazy {
        repository.getMovies("popular")
    }

    val topRatedMovies: LiveData<Resource<EndsResponse>> by lazy {
        repository.getMovies("top_rated")
    }


//    }

    //    fun loadUpcoming() = liveData(Dispatchers.IO) {
//        emit(Resource.loading())
//        try {
//            // Fetch data from remote
//            val apiResponse = repository.getUpcomingMovie()
//            emit(Resource.success(apiResponse))
//        } catch (e: Exception) {
//            if (e is SocketTimeoutException)
//                emit(Resource.error("Something went wrong!"))
//        }
//    }
//
//    fun loadPopular() = liveData(Dispatchers.IO) {
//        emit(Resource.loading())
//        try {
//            // Fetch data from remote
//            val apiResponse = repository.getPopularMovie()
//            emit(Resource.success(apiResponse))
//        } catch (e: Exception) {
//            if (e is SocketTimeoutException)
//                emit(Resource.error("Something went wrong!"))
//        }
//    }
//
//    fun loadTopRated() = liveData(Dispatchers.IO) {
//        emit(Resource.loading())
//        try {
//            // Fetch data from remote
//            val apiResponse = repository.getTopRatedMovie()
//            emit(Resource.success(apiResponse))
//        } catch (e: Exception) {
//            if (e is SocketTimeoutException)
//                emit(Resource.error("Something went wrong!"))
//        }
//    fun loadTrending() = liveData(Dispatchers.IO) {
//        emit(Resource.loading())
//        try {
//            // Fetch data from remote
//            val apiResponse = repository.getTrendingMovie()
//            emit(Resource.success(apiResponse))
//        } catch (e: Exception) {
//            if (e is SocketTimeoutException)
//                emit(Resource.error("Something went wrong!"))
//        }


}