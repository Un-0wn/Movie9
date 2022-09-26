package com.example.main9.ui.repository

import com.example.main9.utils.DataSource
import com.example.main9.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteRepository: RemoteRepository
) : DataSource {

    override fun getMovies(category: String) = performGetOperation {
        remoteRepository.getMovies(category)
    }

    override fun getTrending() = performGetOperation {
        remoteRepository.getTrending()
    }

    override fun getTv(category: String) = performGetOperation {
        remoteRepository.getTv(category)
    }

    override fun kids(category: String) = performGetOperation {
        remoteRepository.getTv(category)
    }



}