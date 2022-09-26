package com.example.main9.ui.fragment.home_.movies_viewall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.ends.Movie
import com.example.main9.databinding.ItemHomeViewallBinding
import com.example.main9.utils.LatestAdapterListener

class IndustryPagingAdapter  (private val latestAdapterListener: LatestAdapterListener) :
    PagingDataAdapter<Movie, IndustryPagingAdapter.LatestViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val binding =
            ItemHomeViewallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class LatestViewHolder(private val binding: ItemHomeViewallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {


            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        latestAdapterListener.openMovie(item)
                    }
                }
            }

            binding.apply {
                Glide.with(itemView)
                    .asBitmap()
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .into(imageView)
            }
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

}