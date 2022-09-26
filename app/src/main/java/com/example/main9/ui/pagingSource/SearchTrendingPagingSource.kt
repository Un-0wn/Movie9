package com.example.main9.ui.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.main9.ends.Ends
import com.example.main9.ends.Movie
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_INDEX_PAGE = 1

class SearchTrendingPagingSource(
    private val ends: Ends,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val position = params.key ?: STARTING_INDEX_PAGE

        return try {
            val response = ends.searchMovie(query, position)
//            val searchTrending = response.results
            val searchTrending = response.body()!!.results
            LoadResult.Page(
                data = searchTrending,
                prevKey = if (position == STARTING_INDEX_PAGE) null else position - 1,
                nextKey = if (searchTrending.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }

}