package com.example.main9.ui.fragment.home_.tv_details

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.main9.R
import com.example.main9.databinding.FragmentTvDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailFragment : Fragment(R.layout.fragment_tv_detail) {

    private var _binding: FragmentTvDetailBinding? = null
    private val binding get() = _binding!!
    private val args: TvDetailFragmentArgs by navArgs()
//    private lateinit var model: TV

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTvDetailBinding.bind(view)

        val model = args.tvArg!!
        binding.apply {

            Glide.with(this@TvDetailFragment)
                .asBitmap()
                .load("${model.baseUrl}${model.poster_path}")
                .centerCrop()
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                }).into(binding.poster)
            name.text = model.original_name
            date.text = model.first_air_date
            rating.text = model.vote_average.toString()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}