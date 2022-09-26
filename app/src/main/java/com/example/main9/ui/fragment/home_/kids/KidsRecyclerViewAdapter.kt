package com.example.main9.ui.fragment.home_.kids

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeBinding
import com.example.main9.databinding.ItemHomeViewallBinding
import com.example.main9.ends.Movie
import com.example.main9.ends.TV

class KidsRecyclerViewAdapter(
    differCallback: DiffUtil.ItemCallback<TV>,
) : RecyclerView.Adapter<KidsRecyclerViewAdapter.KidsVH>() {

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): KidsVH =
        KidsVH(
            ItemHomeViewallBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    override fun onBindViewHolder(holder: KidsVH, position: Int) {
        holder.bindMovieItem(differ.currentList[position])

//        with(holder) {
//
//            when (position) {
//                0 -> {
//                    binding.spacingStart.visibility = View.VISIBLE
//                }
//                differ.currentList.size - 1 -> {
//                    binding.spacingEnd.visibility = View.VISIBLE
//                }
//                else -> {
//                    binding.spacingEnd.visibility = View.GONE
//                    binding.spacingStart.visibility = View.GONE
//                }
//            }
//        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class KidsVH(val binding: ItemHomeViewallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMovieItem(tv: TV) {
            binding.apply {

                Glide.with(itemView)
//                    .load(movie.baseUrl + movie.poster_path)
                    .load("${tv.baseUrl}${tv.poster_path}")
                    .centerCrop()
                    .into(imageView)

            }
        }
    }
}