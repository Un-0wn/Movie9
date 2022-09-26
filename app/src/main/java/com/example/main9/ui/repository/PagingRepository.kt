package com.example.main9.ui.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.main9.ends.Ends
import com.example.main9.ends.Movie
import com.example.main9.ends.TV
import com.example.main9.ui.pagingSource.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagingRepository @Inject constructor(
    private val ends: Ends
) {

    fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = {
                SearchTrendingPagingSource(ends, query)
            }
        ).liveData

    fun getTv(): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                TvPagingSource(
                    ends,
                    "top_rated"
                )
            }
        ).flow
    }

    fun getNow1(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PagingSource(
                    ends,
                    "now_playing"
                )
            }
        ).flow
    }
    fun getUpcoming1(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PagingSource(
                    ends,
                    "upcoming"
                )
            }
        ).flow
    }
    fun getPopular1(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PagingSource(
                    ends,
                    "popular"
                )
            }
        ).flow
    }
    fun getTop1(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PagingSource(
                    ends,
                    "top_rated"
                )
            }
        ).flow
    }

    fun getPopular(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                PopularPagingSource(
                    ends
                )
            }
        ).flow
    }

    fun getTop(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                TopPagingSource(
                    ends
                )
            }
        ).flow
    }

    fun getUpcoming(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
//                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                UpcomingPagingSource(
                    ends
                )
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

}