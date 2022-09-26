package com.example.main9.ui.fragment.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.main9.ui.repository.PagingRepository
import com.example.main9.utils.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val pagingRepository: PagingRepository
) : ViewModel() {

    fun searchMovie(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            val apiResponse =
                pagingRepository.getSearchResult(query).cachedIn(viewModelScope)
            emit(Resource.success(apiResponse))
        } catch (e: Exception) {
            if (e is SocketTimeoutException)
                emit(Resource.error("Something went wrong!"))
        }
    }

    fun getSearchMovie(query: String) =
        pagingRepository.getSearchResult(query).cachedIn(viewModelScope)

}