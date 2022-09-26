package com.example.main9.ui.fragment.home_.home_

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeBinding
import com.example.main9.ends.Movie
import com.example.main9.ui.fragment.home.HomeFragmentDirections

class MoviesHorizontalAdapter(
    differCallback: DiffUtil.ItemCallback<Movie>,
) : RecyclerView.Adapter<MoviesHorizontalAdapter.MoviesHorizontalVH>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MoviesHorizontalVH =
        MoviesHorizontalVH(
            ItemHomeBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesHorizontalVH, position: Int) {
        holder.bindMovieItem(differ.currentList[position])

//        val currentJob = differ.currentList[position]
//        holder.bindMovieItem(currentJob)
//        holder.itemView.setOnClickListener { mView->
//            val direction =HomeFragmentDirections.actionHomeFragmentToMoviesDetailFragment()
//            mView.findNavController().navigate(direction)
//        }


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

    inner class MoviesHorizontalVH(val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovieItem(movie: Movie) {

//            binding.root.setOnClickListener {
//                Log.d("ids", "launch ids${movie.id} position $bindingAdapterPosition")
//                val position = bindingAdapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                        onClick.openMovie(movie)
//                }
//            }
            binding.apply {

                Glide.with(itemView)
                    .asBitmap()
//                    .load(movie.baseUrl + movie.poster_path)
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .into(movieImage)

            }
        }
    }
}