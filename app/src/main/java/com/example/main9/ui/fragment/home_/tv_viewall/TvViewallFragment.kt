package com.example.main9.ui.fragment.home_.tv_viewall

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.MViewallFragmentBinding
import com.example.main9.databinding.TvViewallFragmentBinding
import com.example.main9.ends.TV
import com.example.main9.ui.activity.MainActivity
import com.example.main9.ui.fragment.home_.movies_viewall.IndustryPagingAdapter
import com.example.main9.ui.fragment.home_.movies_viewall.MViewallFragmentDirections
import com.example.main9.ui.fragment.home_.movies_viewall.MViewallViewModel
import com.example.main9.utils.LatestAdapterListener
import com.example.main9.utils.TvAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TvViewallFragment : Fragment(R.layout.tv_viewall_fragment), TvAdapterListener {

    private var _binding: TvViewallFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TvViewallViewModel by viewModels()
    private lateinit var tvPagingAdapter: TvPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = TvViewallFragmentBinding.bind(view)

        tvPagingAdapter = TvPagingAdapter(this)
        binding.apply {
            rvTv.adapter = tvPagingAdapter
        }

        lifecycleScope.launchWhenResumed {
            viewModel.tv.collectLatest {
                tvPagingAdapter.submitData(it)
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

    override fun openTv(model: TV) {
        val action = TvViewallFragmentDirections.actionTvViewallFragmentToTvDetailFragment(model)
        findNavController().navigate(action)
    }


}