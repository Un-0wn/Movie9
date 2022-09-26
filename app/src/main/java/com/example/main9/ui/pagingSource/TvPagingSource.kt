package com.example.main9.ui.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.main9.ends.Ends
import com.example.main9.ends.TV
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_PAGE = 1

class TvPagingSource(
    private val ends: Ends,
    private val category: String
) : PagingSource<Int, TV>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TV> {

        val position = params.key ?: DEFAULT_PAGE

        return try {

            val response = ends.getSeries(category, position)
            val data = response.body()!!.results

            LoadResult.Page(
                data = data,
                prevKey = if (position == DEFAULT_PAGE) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TV>): Int {
        return 1
    }

}