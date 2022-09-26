package com.example.main9.ui.fragment.home_.home_viewall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.main9.ui.repository.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewallViewModel @Inject constructor(
    pagingRepository: PagingRepository
) : ViewModel() {

    val popular = pagingRepository
        .getPopular()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    val top = pagingRepository
        .getTop()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    val upcoming = pagingRepository
        .getUpcoming()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

}