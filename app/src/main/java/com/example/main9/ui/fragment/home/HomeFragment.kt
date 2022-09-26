package com.example.main9.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.main9.R
import com.example.main9.databinding.FragmentHomeBinding
import com.example.main9.ui.fragment.home_.home_.Home2Fragment
import com.example.main9.ui.fragment.home_.kids.KidsFragment
import com.example.main9.ui.fragment.home_.movies.MoviesFragment
import com.example.main9.ui.fragment.home_.tv.TvFragment
import com.example.main9.utils.MovieViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

//        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setUpTabs()

    }

    private fun setUpTabs() {
        binding.apply {
//            toolbar.setBackgroundColor(Color.TRANSPARENT)
            tabs.setupWithViewPager(viewPager)
            val viewPagerAdapter =
                MovieViewPagerAdapter(childFragmentManager)
            viewPagerAdapter.apply {
                addFragment(Home2Fragment(), "Home")
                addFragment(MoviesFragment(), "Movies")
                addFragment(TvFragment(), "Tv")
                addFragment(KidsFragment(), "Kids")
            }
            viewPager.apply {
                adapter = viewPagerAdapter
                offscreenPageLimit = 4
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}