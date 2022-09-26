package com.example.main9.ui.fragment.home_.home_

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeSliderBinding
import com.example.main9.ends.Movie

class HomeSliderRecyclerViewAdapter1(
    val context: Context,
    val movies: ArrayList<Movie>
) : RecyclerView.Adapter<HomeSliderRecyclerViewAdapter1.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding =
            ItemHomeSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val currentItem = movies[position]
        holder.bind(currentItem)

        with(holder) {

            when (position) {
                0 -> {
                    binding.spacingStart.visibility = View.VISIBLE
                }
                movies.size - 1 -> {
                    binding.spacingEnd.visibility = View.VISIBLE
                }
                else -> {
                    binding.spacingEnd.visibility = View.GONE
                    binding.spacingStart.visibility = View.GONE
                }
            }
        }
    }

    inner class SliderViewHolder(val binding: ItemHomeSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {

            binding.apply {

                Glide.with(itemView)
                    .asBitmap()
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .into(movieImage)

            }
        }

    }

    override fun getItemCount() = movies.size

}