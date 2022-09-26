package com.example.main9.ui.fragment.home_.movies_viewall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.main9.ui.repository.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MViewallViewModel @Inject constructor(
    pagingRepository: PagingRepository
) : ViewModel() {

    val latest = pagingRepository
        .getNow1()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    val upcoming = pagingRepository
        .getUpcoming1()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    val popular = pagingRepository
        .getPopular1()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)

    val top = pagingRepository
        .getTop1()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)


}