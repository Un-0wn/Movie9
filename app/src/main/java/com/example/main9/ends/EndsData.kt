package com.example.main9.ends

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @Expose val id: Int?,
    @Expose val original_title: String?,
    @Expose val poster_path: String?,
    @Expose val backdrop_path: String?,
    @Expose val overview: String?,
    @Expose val release_date: String?,
    @Expose val vote_average: Double?,
    @Expose val adult: Boolean?
) : Parcelable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}

@Parcelize
data class TV(
    @Expose val id: Int?,
    @Expose val original_name: String?,
    @Expose val poster_path: String?,
    @Expose val overview: String?,
    @Expose val vote_average: Double?,
    @Expose val first_air_date: String?
) : Parcelable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}

@Parcelize
data class SearchTrending(
    val poster_path: String?,
    val adult: Boolean?,
    val overview: String?,
    val release_date: String?,
    val id: Int?,
    val original_title: String?,
    val backdrop_path: String?,
    val vote_count: Double?
) : Parcelable {
    val baseUrl get() = "https://image.tmdb.org/t/p/w500"
}