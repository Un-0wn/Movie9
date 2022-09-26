package com.example.main9.ends

import com.example.main9.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Ends {

//    companion object {
//        const val BASE_URL = "https://api.themoviedb.org/3/"
//        const val API_KEY = "bbdfdb1990ee90afc92659c1f44d2ff4"
//    }

    @GET("movie/{category}?api_key=$API_KEY")
    suspend fun getMovieResponse(
        @Path("category") category: String,
        @Query("page") page: Int = 1
    ): Response<EndsResponse>

    @GET("tv/{category}?api_key=$API_KEY")
    suspend fun getSeries(
        @Path("category") category: String,
        @Query("page") page: Int =1
    ): Response<TVResponse>

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(
        @Query("page") page : Int = 1
    ): Response<EndsResponse>

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedMovies(
        @Query("page") page : Int = 1
    ): Response<EndsResponse>

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(
        @Query("page") page : Int = 1
    ): Response<EndsResponse>


    @GET("trending/all/day?api_key=$API_KEY")
    suspend fun getTrending(
//        @Query("page") page : Int = 1
    ): Response<EndsResponse>

    // Search
    @GET("search/movie?api_key=$API_KEY")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Response<EndsResponse>

}