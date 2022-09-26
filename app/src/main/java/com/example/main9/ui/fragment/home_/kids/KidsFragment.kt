package com.example.main9.ui.fragment.home_.kids

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.main9.R
import com.example.main9.databinding.KidsFragmentBinding
import com.example.main9.ends.TV
import com.example.main9.utils.showToast
import com.example.main9.utils.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KidsFragment : Fragment(R.layout.kids_fragment) {

    private var _binding: KidsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: KidsViewModel by viewModels()

    private var kidsList: ArrayList<TV> = ArrayList()

    private lateinit var kidsAdapter: KidsRecyclerViewAdapter1

//    @Inject
//    lateinit var kidsAdapter: KidsRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = KidsFragmentBinding.bind(view)

        initAdapters()
        fetchData()
    }

    private fun initAdapters() {
        binding.apply {
            kidsAdapter = KidsRecyclerViewAdapter1(requireContext(), kidsList)
            rvKids.adapter = kidsAdapter
        }
    }

    private fun fetchData() {
//        lifecycleScope.launchWhenResumed {
//            viewModel.kids.observe(viewLifecycleOwner) { response ->
//                when (response.status) {
//                    Status.SUCCESS -> {
//                        binding.kidsProgress.visibility = View.GONE
//                        kidsAdapter.differ.submitList(response.data?.results)
//                    }
//                    Status.ERROR -> showToast(response.message.toString())
//                    Status.LOADING -> binding.kidsProgress.visibility = View.VISIBLE
//                }
//            }
//        }

        lifecycleScope.launchWhenResumed {
            viewModel.kids.observe(requireActivity(), { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        binding.kidsProgress.visibility =View.GONE
//                    topAdapter.differ.submitList(response.data?.results)
                        kidsList.clear()
                        kidsList.addAll(res.data!!.results)
                        kidsAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> showToast(res.message.toString())
                    Status.LOADING -> {
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}