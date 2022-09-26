package com.example.main9.ui.fragment.home_.movies_details

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
import com.example.main9.databinding.FragmentMoviesDetailBinding
import com.example.main9.ends.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment : Fragment(R.layout.fragment_movies_detail) {

    private var _binding: FragmentMoviesDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MoviesDetailFragmentArgs by navArgs()
//    private lateinit var model: Movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesDetailBinding.bind(view)

        val model = args.moviesArg!!
        binding.apply {

            Glide.with(this@MoviesDetailFragment)
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
            name.text = model.original_title
            date.text = model.release_date
            rating.text = model.vote_average.toString()
        }

    }

}