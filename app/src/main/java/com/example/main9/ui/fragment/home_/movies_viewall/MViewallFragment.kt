package com.example.main9.ui.fragment.home_.movies_viewall

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.MViewallFragmentBinding
import com.example.main9.ends.Movie
import com.example.main9.ui.activity.MainActivity
import com.example.main9.utils.LatestAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MViewallFragment : Fragment(R.layout.m_viewall_fragment), LatestAdapterListener {

    private var _binding: MViewallFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MViewallViewModel by viewModels()
    private lateinit var industryAdapter: IndustryPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MViewallFragmentBinding.bind(view)

        industryAdapter = IndustryPagingAdapter(this)
        binding.apply {
            rvAllMovies.adapter = industryAdapter
        }

//        val pageType = requireArguments().get("view")

        when (requireArguments().get("view")) {
            "viewB" -> viewB()
            "viewHH" -> viewHH()
            "viewH" -> viewH()
            "viewT" -> viewT()
        }

    }

    private fun viewB() {
        lifecycleScope.launchWhenResumed {
            viewModel.latest.collectLatest {
                industryAdapter.submitData(it)
            }
        }
    }

    private fun viewHH() {
        lifecycleScope.launchWhenResumed {
            viewModel.upcoming.collectLatest {
                industryAdapter.submitData(it)
            }
        }
    }

    private fun viewH() {
        lifecycleScope.launchWhenResumed {
            viewModel.popular.collectLatest {
                industryAdapter.submitData(it)
            }
        }
    }

    private fun viewT() {
        lifecycleScope.launchWhenResumed {
            viewModel.top.collectLatest {
                industryAdapter.submitData(it)
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
        val action = MViewallFragmentDirections.actionMViewallFragmentToMoviesDetailFragment(model)
        findNavController().navigate(action)
    }

}