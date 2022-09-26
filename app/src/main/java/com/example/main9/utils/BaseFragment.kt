package com.example.main9.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import com.example.main9.R
import com.google.android.material.snackbar.Snackbar
import com.example.main9.utils.vo.Resource
import com.example.main9.utils.vo.Status
import kotlinx.android.synthetic.main.home2_fragment.*
import timber.log.Timber

open class BaseFragment : Fragment() {

    fun showActionSnackbar(msg: Int, actionName: Int, actionListener: View.OnClickListener) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT)
            .setAction(actionName, actionListener)
            .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.background_light))
            .show()
    }

    fun showMessageSnackbar(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT)
            .show()
    }

//    private fun hideShimmer(container: ShimmerFrameLayout) {
//        container.hideShimmer()
//        container.visibility = View.GONE
//    }

    fun <R, D> handleResponseResult(
        resource: Resource<R>,
        list: List<D>,
        differ: AsyncListDiffer<D>,
//        shimmerContainer: ShimmerFrameLayout
    ) {
        resource.data?.let { }
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let {
                    differ.submitList(list)
                    progressBar.visibility = View.GONE
//                    hideShimmer(shimmerContainer)
                }
            }
            Status.ERROR -> {
                Timber.e("Error")
                Timber.e(resource.message.toString())
                showMessageSnackbar(resource.message!!)
//                hideShimmer(shimmerContainer)
            }
            Status.LOADING -> progressBar.visibility = View.VISIBLE
        }
    }
}
