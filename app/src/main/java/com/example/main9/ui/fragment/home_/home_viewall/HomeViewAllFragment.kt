package com.example.main9.ui.fragment.home_.home_viewall

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.HomeViewallFragmentBinding
import com.example.main9.ends.Movie
import com.example.main9.ui.activity.MainActivity
import com.example.main9.utils.Constants
import com.example.main9.utils.LatestAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.e

@AndroidEntryPoint
class HomeViewAllFragment : Fragment(R.layout.home_viewall_fragment), LatestAdapterListener {

    private var _binding: HomeViewallFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewallViewModel by viewModels()
    private lateinit var movieAdapter: LatestPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = HomeViewallFragmentBinding.bind(view)

        movieAdapter = LatestPagingAdapter(this)

        binding.apply {
            rvMovie.adapter = movieAdapter
        }

        //        binding.pageTitle.text = pageType.toString()

        when (requireArguments().get(Constants.viewAll)) {
            Constants.Popular -> fetchPopular()
            Constants.TopRated -> fetchTop()
            Constants.Upcoming -> fetchUp()
        }
        Timber.e("CONSTANTS type")
        e(Constants.toString())

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun fetchPopular() {
//        viewModel.movies().observe(viewLifecycleOwner) {
//
//            movieAdapter.submitData(viewLifecycleOwner.lifecycle, it)
//
//        }
        lifecycleScope.launchWhenResumed {
            viewModel.popular.collectLatest {
                movieAdapter.submitData(it)
            }
        }

    }

    private fun fetchTop() {
        lifecycleScope.launchWhenResumed {
            viewModel.top.collectLatest {
                movieAdapter.submitData(it)
            }
        }

    }

    private fun fetchUp() {

        lifecycleScope.launchWhenResumed {
            viewModel.upcoming.collectLatest {
                movieAdapter.submitData(it)
            }
        }

    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).hideBottomNavigation()
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNavigation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openMovie(model: Movie) {
        val action = HomeViewAllFragmentDirections.actionHomeViewAllFragmentToMoviesDetailFragment(model)
        findNavController().navigate(action)
    }


}