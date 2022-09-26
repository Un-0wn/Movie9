package com.example.main9.ui.fragment.home_.tv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.main9.databinding.ItemHomeViewallBinding
import com.example.main9.ends.TV
import com.example.main9.ui.fragment.home.HomeFragmentDirections
import com.example.main9.ui.fragment.home_.tv_details.TvDetailFragment

//@ExperimentalCoroutinesApi
class TvCategoryRecyclerViewAdapter1(
    val context: Context,
    val tv: ArrayList<TV>
) : RecyclerView.Adapter<TvCategoryRecyclerViewAdapter1.LatestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val binding =
            ItemHomeViewallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val currentItem = tv[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { mView ->
            val direction = HomeFragmentDirections.actionHomeFragmentToTvDetailFragment(currentItem)
            mView.findNavController().navigate(direction)
        }

//        with(holder) {
//
//            when (position) {
//                0 -> {
//                    binding.spacingStart.visibility = View.VISIBLE
//                }
//                tv.size - 1 -> {
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


        fun bind(tv: TV) {

            binding.apply {

                Glide.with(itemView)
                    .asBitmap()
                    .skipMemoryCache(false)
                    .load("${tv.baseUrl}${tv.poster_path}")
                    .centerCrop()
                    .into(imageView)

//                textMovieName.text = movie.original_title
            }
        }

    }

    override fun getItemCount() = tv.size

}