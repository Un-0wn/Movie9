package com.example.main9.ui.fragment.home_.home_

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.Home2FragmentBinding
import com.example.main9.ends.Movie
import com.example.main9.utils.Constants
import com.example.main9.utils.showToast
import com.example.main9.utils.vo.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class Home2Fragment : Fragment(R.layout.home2_fragment), View.OnClickListener {

    private var _binding: Home2FragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Home2ViewModel by viewModels()

//    @Inject
//    lateinit var upAdapter: MoviesHorizontalAdapter
//
//    @Inject
//    lateinit var popAdapter: MoviesHorizontalAdapter
//
//    @Inject
//    lateinit var topAdapter: MoviesHorizontalAdapter
//
//    @Inject
//    lateinit var nowAdapter: HomeSliderRecyclerViewAdapter

    private var upcomingMovieList: ArrayList<Movie> = ArrayList()
    private var popularMovieList: ArrayList<Movie> = ArrayList()
    private var topRatedMovieList: ArrayList<Movie> = ArrayList()
    private var trendingMovieList: ArrayList<Movie> = ArrayList()

    private lateinit var upAdapter: HomeRecyclerViewAdapter
    private lateinit var popAdapter: HomeRecyclerViewAdapter
    private lateinit var topAdapter: HomeRecyclerViewAdapter
    private lateinit var trendingAdapter: HomeSliderRecyclerViewAdapter1

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = Home2FragmentBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = Home2FragmentBinding.bind(view)

        initAdapters()
        fetchData1()
        binding.viewAllPopular.setOnClickListener(this)
        binding.viewAllUpcoming.setOnClickListener(this)
        binding.viewAllTopRated.setOnClickListener(this)
    }

    private fun initAdapters() {

        binding.apply {
            upAdapter = HomeRecyclerViewAdapter(requireContext(), upcomingMovieList)
            recyclerViewUpcoming.adapter = upAdapter
//
            popAdapter = HomeRecyclerViewAdapter(requireContext(), popularMovieList)
            recyclerViewPopular.adapter = popAdapter
//
            topAdapter = HomeRecyclerViewAdapter(requireContext(), topRatedMovieList)
            recyclerViewTopRated.adapter = topAdapter

            trendingAdapter = HomeSliderRecyclerViewAdapter1(requireContext(), trendingMovieList)
            recyclerViewSlider.adapter = trendingAdapter
        }
    }

    /*private fun fetchData() {

        viewModel.trendingMovies.observe(viewLifecycleOwner) { response ->
            response.data?.results?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    nowAdapter.differ,
                )
            }
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) { response ->
            response.data?.results?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    upAdapter.differ,
                )
            }
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) { response ->
            response.data?.results?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    popAdapter.differ,
                )
            }
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) { response ->
            response.data?.results?.let { list ->
                handleResponseResult(
                    response,
                    list,
                    topAdapter.differ,
                )
            }
            *//* when (response.status) {
                Status.SUCCESS -> {
                    topAdapter.differ.submitList(response.data?.results)
                }
                Status.ERROR -> showToast(response.message.toString())
                Status.LOADING -> {}
            }
        }*//*// Without BaseFragment

        }
    }*/

    private fun fetchData1() {

        viewModel.trendingMovies.observe(requireActivity(), { res ->
            when (res.status) {
                Status.SUCCESS -> {
//                    topAdapter.differ.submitList(response.data?.results)
                    trendingMovieList.clear()
                    trendingMovieList.addAll(res.data!!.results)
                    trendingAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> showToast(res.message.toString())
                Status.LOADING -> {
                }
            }
        })

        viewModel.popularMovies.observe(requireActivity(), { res ->
            when (res.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
//                    topAdapter.differ.submitList(response.data?.results)
                    popularMovieList.clear()
                    popularMovieList.addAll(res.data!!.results)
                    popAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> showToast(res.message.toString())
                Status.LOADING -> {

                }
            }
        })

        viewModel.topRatedMovies.observe(requireActivity(), { res ->
            when (res.status) {
                Status.SUCCESS -> {
//                    topAdapter.differ.submitList(response.data?.results)
                    topRatedMovieList.clear()
                    topRatedMovieList.addAll(res.data!!.results)
                    topAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> showToast(res.message.toString())
                Status.LOADING -> {
                }
            }
        })

        viewModel.upcomingMovies.observe(requireActivity(), { res ->
            when (res.status) {
                Status.SUCCESS -> {
//                    topAdapter.differ.submitList(response.data?.results)
                    upcomingMovieList.clear()
                    upcomingMovieList.addAll(res.data!!.results)
                    upAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> showToast(res.message.toString())
                Status.LOADING -> {
                }
            }
        })

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.view_all_popular -> {
                val popularBundle = bundleOf(Constants.viewAll to Constants.Popular)
                findNavController().navigate(
                    R.id.action_homeFragment_to_homeViewAllFragment,
                    popularBundle
                )
                Timber.e(popularBundle.toString())
            }
            R.id.view_all_top_rated -> {
                val topBundle = bundleOf(Constants.viewAll to Constants.TopRated)
                findNavController().navigate(
                    R.id.action_homeFragment_to_homeViewAllFragment,
                    topBundle
                )
                Timber.e(topBundle.toString())
            }
            R.id.view_all_upcoming -> {
                val upcomingBundle = bundleOf(Constants.viewAll to Constants.Upcoming)
                findNavController().navigate(
                    R.id.action_homeFragment_to_homeViewAllFragment,
                    upcomingBundle
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}