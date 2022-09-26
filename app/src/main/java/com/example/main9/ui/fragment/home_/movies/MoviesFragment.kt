package com.example.main9.ui.fragment.home_.movies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.MoviesFragmentBinding
import com.example.main9.ends.Movie
import com.example.main9.ends.TV
import com.example.main9.ui.fragment.home_.kids.KidsRecyclerViewAdapter1
import com.example.main9.utils.showToast
import com.example.main9.utils.vo.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.movies_fragment), View.OnClickListener {

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()

    private var kidsList: ArrayList<Movie> = ArrayList()

    private lateinit var moviesAdapter: MoviesRecyclerViewAdapter1

//    @Inject
//    lateinit var categoryAdapter: CategoryRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MoviesFragmentBinding.bind(view)


        initAdapters()
        fetchData()
        binding.viewB.setOnClickListener(this)
        binding.viewHH.setOnClickListener(this)
        binding.viewH.setOnClickListener(this)
        binding.viewT.setOnClickListener(this)

    }

    private fun initAdapters() {
        binding.apply {
            moviesAdapter = MoviesRecyclerViewAdapter1(requireContext(), kidsList)
            rvMovie.adapter = moviesAdapter
        }
    }


    private fun fetchData() {


//        lifecycleScope.launchWhenResumed {
            viewModel.trendingMovies.observe(requireActivity(), { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        binding.moviesProgress.visibility = View.GONE
//                    topAdapter.differ.submitList(response.data?.results)
                        kidsList.clear()
                        kidsList.addAll(res.data!!.results)
                        moviesAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> showToast(res.message.toString())
                    Status.LOADING -> {
                    }
                }
            })
//        }

//        viewModel.trendingMovies.observe(viewLifecycleOwner) { response ->
//
//            when (response.status) {
//                Status.SUCCESS -> {
//                    binding.moviesProgress.visibility = View.GONE
//                    categoryAdapter.differ.submitList(response.data?.results)
//                }
//                Status.ERROR -> showToast(response.message.toString())
//                Status.LOADING -> binding.moviesProgress.visibility = View.VISIBLE
//            }
//        }
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.viewB -> {
                val bundle = bundleOf("view" to "viewB")
                findNavController().navigate(R.id.action_homeFragment_to_mViewallFragment, bundle)
            }
            R.id.viewHH -> {
                val bundle = bundleOf("view" to "viewHH")
                findNavController().navigate(R.id.action_homeFragment_to_mViewallFragment, bundle)
            }
            R.id.viewH -> {
                val bundle = bundleOf("view" to "viewH")
                findNavController().navigate(R.id.action_homeFragment_to_mViewallFragment, bundle)
            }
            R.id.viewT -> {
                val bundle = bundleOf("view" to "viewT")
                findNavController().navigate(R.id.action_homeFragment_to_mViewallFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
