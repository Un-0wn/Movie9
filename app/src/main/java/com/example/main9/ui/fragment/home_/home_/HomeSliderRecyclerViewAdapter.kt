package com.example.main9.ui.fragment.home_.home_

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeBinding
import com.example.main9.databinding.ItemHomeSliderBinding
import com.example.main9.ends.Movie

class HomeSliderRecyclerViewAdapter(
    differCallback: DiffUtil.ItemCallback<Movie>,
) : RecyclerView.Adapter<HomeSliderRecyclerViewAdapter.MoviesHorizontalVH>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesHorizontalVH =
        MoviesHorizontalVH(
            ItemHomeSliderBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHorizontalVH, position: Int) {
        holder.bindMovieItem(differ.currentList[position])

        with(holder) {

            when (position) {
                0 -> {
                    binding.spacingStart.visibility = View.VISIBLE
                }
                differ.currentList.size - 1 -> {
                    binding.spacingEnd.visibility = View.VISIBLE
                }
                else -> {
                    binding.spacingEnd.visibility = View.GONE
                    binding.spacingStart.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class MoviesHorizontalVH(val binding: ItemHomeSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovieItem(movie: Movie) {
            binding.apply {

                Glide.with(itemView)
//                    .load(movie.baseUrl + movie.poster_path)
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .into(movieImage)

            }
        }
    }
}