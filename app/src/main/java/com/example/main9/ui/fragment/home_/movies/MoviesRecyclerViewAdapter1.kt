package com.example.main9.ui.fragment.home_.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeBinding
import com.example.main9.databinding.ItemHomeViewallBinding
import com.example.main9.ends.Movie
import com.example.main9.ui.fragment.home.HomeFragmentDirections

//@ExperimentalCoroutinesApi
class MoviesRecyclerViewAdapter1(
    val context: Context,
    val movies: ArrayList<Movie>
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter1.LatestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val binding =
            ItemHomeViewallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val currentItem = movies[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections.actionHomeFragmentToMoviesDetailFragment(currentItem)
            mView.findNavController().navigate(direction)
        }

//        with(holder) {
//
//            when (position) {
//                0 -> {
//                    binding.spacingStart.visibility = View.VISIBLE
//                }
//                movies.size - 1 -> {
//                    binding.spacingEnd.visibility = View.VISIBLE
//                }
//                else -> {
//                    binding.spacingEnd.visibility = View.GONE
//                    binding.spacingStart.visibility = View.GONE
//                }
//            }
//        }
    }

    inner class LatestViewHolder(val binding: ItemHomeViewallBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(movie: Movie) {

            binding.apply {

                Glide.with(itemView)
                    .asBitmap()
                    .skipMemoryCache(false)
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .into(imageView)

//                textMovieName.text = movie.original_title
            }
        }

    }

    override fun getItemCount() = movies.size

}