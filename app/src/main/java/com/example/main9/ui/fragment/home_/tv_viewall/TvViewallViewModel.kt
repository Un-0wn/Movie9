package com.example.main9.ui.fragment.home_.tv_viewall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.main9.ui.repository.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class TvViewallViewModel @Inject constructor(
    pagingRepository: PagingRepository
) : ViewModel() {

    val tv = pagingRepository
        .getTv()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
}