package com.example.main9.ui.fragment.home_.kids

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.main9.ends.TVResponse
import com.example.main9.ui.repository.Repository
import com.example.main9.utils.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KidsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val kids: LiveData<Resource<TVResponse>> by lazy {
        repository.getTv("airing_today")
    }
}