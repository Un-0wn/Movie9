package com.example.main9.ui.fragment.searchMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.main9.R
import com.example.main9.databinding.FragmentSearchMainBinding
import com.example.main9.databinding.TvFragmentBinding

class SearchMainFragment : Fragment(R.layout.fragment_search_main) {

    private var _binding: FragmentSearchMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchMainBinding.bind(view)

        toolbarSetup()
        binding.searchCard.setOnClickListener {
            findNavController().navigate(R.id.action_searchMainFragment_to_searchFragment)
        }

    }

    private fun toolbarSetup() {
        binding.searchToolbar
    }

}