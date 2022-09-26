package com.example.main9.ui.fragment.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.main9.R
import com.example.main9.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber.e

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private var _binding: SearchFragmentBinding? = null
    private val viewModel by viewModels<SearchViewModel>()
    private val binding get() = _binding!!
    private lateinit var searchadapter: SearchPagingAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)

        searchadapter = SearchPagingAdapter()

        binding.rvSearch.apply {
            adapter = searchadapter
        }
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                getSearchResult()
            }

        })

        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getSearchResult()
            }
            false
        }

        return binding.root
    }

    private fun getSearchResult() {
        if (!binding.searchEditText.text.isNullOrEmpty())
            viewModel.getSearchMovie(binding.searchEditText.text.toString())
                .observe(viewLifecycleOwner, {
                    searchadapter.submitData(viewLifecycleOwner.lifecycle, it)
                    e(it.toString())
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}