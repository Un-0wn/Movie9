package com.example.main9.ui.fragment.home_.tv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.TvFragmentBinding
import com.example.main9.ends.TV
import com.example.main9.ui.fragment.home_.kids.KidsRecyclerViewAdapter1
import com.example.main9.ui.fragment.home_.movies_viewall.MViewallFragmentDirections
import com.example.main9.utils.LatestAdapterListener
import com.example.main9.utils.TvAdapterListener
import com.example.main9.utils.showToast
import com.example.main9.utils.vo.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TvFragment : Fragment(R.layout.tv_fragment) {

    private var _binding: TvFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvViewModel by viewModels()

    private var kidsList: ArrayList<TV> = ArrayList()

    private lateinit var tvSliderAdapter: TVSliderRecyclerViewAdapter1
    private lateinit var tvCategoryAdapter: TvCategoryRecyclerViewAdapter1

//    @Inject
//    lateinit var tvSliderAdapter: TVSliderRecyclerViewAdapter
//
//    @Inject
//    lateinit var tvCategoryAdapter: TvCategoryRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = TvFragmentBinding.bind(view)

        initAdapters()
        fetchData()
        binding.viewAllTV.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tvViewallFragment)
        }
    }

    private fun initAdapters() {
        binding.apply {
            tvSliderAdapter = TVSliderRecyclerViewAdapter1(requireContext(), kidsList)
            rvSliderTv.adapter = tvSliderAdapter

            tvCategoryAdapter = TvCategoryRecyclerViewAdapter1(requireContext(), kidsList,)
            rvTV.adapter = tvCategoryAdapter
        }
    }

    private fun fetchData() {

        lifecycleScope.launchWhenResumed {
            viewModel.topTv.observe(requireActivity(), { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        binding.tvProgress.visibility = View.GONE
//                    topAdapter.differ.submitList(response.data?.results)
                        kidsList.clear()
                        kidsList.addAll(res.data!!.results)
                        tvSliderAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> showToast(res.message.toString())
                    Status.LOADING -> {
                    }
                }
            })
        }
        lifecycleScope.launchWhenResumed {
            viewModel.popularTv.observe(requireActivity(), { res ->
                when (res.status) {
                    Status.SUCCESS -> {
//                    topAdapter.differ.submitList(response.data?.results)
                        kidsList.clear()
                        kidsList.addAll(res.data!!.results)
                        tvCategoryAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> showToast(res.message.toString())
                    Status.LOADING -> {
                    }
                }
            })
        }

//        lifecycleScope.launchWhenResumed {
//            viewModel.topTv.observe(viewLifecycleOwner) { response ->
//                when (response.status) {
//                    Status.SUCCESS -> {
//                        binding.tvProgress.visibility = View.GONE
//                        tvSliderAdapter.differ.submitList(response.data?.results)
//                    }
//                    Status.ERROR -> showToast(response.message.toString())
//                    Status.LOADING -> binding.tvProgress.visibility = View.VISIBLE
//                }
//            }
//        }
//        lifecycleScope.launchWhenResumed {
//            viewModel.popularTv.observe(viewLifecycleOwner) { response ->
//                when (response.status) {
//                    Status.SUCCESS -> {
//                        tvCategoryAdapter.differ.submitList(response.data?.results)
//                    }
//                    Status.ERROR -> showToast(response.message.toString())
//                    Status.LOADING -> {
//                    }
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}