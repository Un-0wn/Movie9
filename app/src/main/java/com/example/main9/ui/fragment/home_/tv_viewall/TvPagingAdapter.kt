package com.example.main9.ui.fragment.home_.tv_viewall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeViewallBinding
import com.example.main9.ends.TV
import com.example.main9.utils.LatestAdapterListener
import com.example.main9.utils.TvAdapterListener

class TvPagingAdapter (private val tvAdapterListener: TvAdapterListener)  :
    PagingDataAdapter<TV, TvPagingAdapter.TvViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding =
            ItemHomeViewallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class TvViewHolder(private val binding: ItemHomeViewallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tv: TV) {

            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        tvAdapterListener.openTv(item)
                    }
                }
            }

            binding.apply {
                Glide.with(itemView)
                    .asBitmap()
                    .load("${tv.baseUrl}${tv.poster_path}")
                    .centerCrop()
                    .into(imageView)
            }
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TV>() {
            override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
                return oldItem == newItem
            }

        }
    }

}