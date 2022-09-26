package com.example.main9.ui.fragment.home_.home_

import androidx.recyclerview.widget.DiffUtil
import com.example.main9.ends.Movie
import com.example.main9.ends.TV
import com.example.main9.ui.fragment.home_.kids.KidsRecyclerViewAdapter
import com.example.main9.ui.fragment.home_.movies.CategoryRecyclerViewAdapter
import com.example.main9.ui.fragment.home_.tv.TVSliderRecyclerViewAdapter
import com.example.main9.ui.fragment.home_.tv.TvCategoryRecyclerViewAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {

    @Provides
    fun provideDifferMovieCallback() =
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }

    @Provides
    fun provideDifferTvCallback() =
        object : DiffUtil.ItemCallback<TV>() {
            override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
                return oldItem == newItem
            }
        }


    @Provides
    fun provideMoviesHorizontalAdapter(
        itemCallback: DiffUtil.ItemCallback<Movie>
    ) = MoviesHorizontalAdapter(itemCallback)

    @Provides
    fun provideHomeSliderAdapter(
        itemCallback: DiffUtil.ItemCallback<Movie>
    ) = HomeSliderRecyclerViewAdapter(itemCallback)

    @Provides
    fun provideMoviesAdapter(
        itemCallback: DiffUtil.ItemCallback<Movie>
    ) = CategoryRecyclerViewAdapter(itemCallback)

    @Provides
    fun provideTvSliderAdapter(
        itemCallback: DiffUtil.ItemCallback<TV>
    ) = TVSliderRecyclerViewAdapter(itemCallback)

    @Provides
    fun provideTvCategoryAdapter(
        itemCallback: DiffUtil.ItemCallback<TV>
    ) = TvCategoryRecyclerViewAdapter(itemCallback)

    @Provides
    fun provideKidsAdapter(
        itemCallback: DiffUtil.ItemCallback<TV>
    ) = KidsRecyclerViewAdapter(itemCallback)
}