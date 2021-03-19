package com.abel.mercadoaea.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.api.ContsApi.Companion.MODO_CATEGORY
import com.abel.mercadoaea.data.model.category.ResponseCategory
import com.abel.mercadoaea.data.model.category.ResponseCategoryItem
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.toast
import com.abel.mercadoaea.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapterCategory: CategoryAdapter by inject()
    private val itemListener =
        OnClickItemRecyclerListener<ResponseCategoryItem> { category -> goToSearched(categoryItem = category) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewCategory.adapter = adapterCategory

        viewModel.liveDataCategory.observe(::getLifecycle, ::updateUI)
        viewModel.getCategory()

    }

    private fun updateUI(data: Data<ResponseCategory>) {
        when (data.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
            }
            Status.SUCCESSFUL -> {
                data.data?.let { configData(it) }
            }
        }
    }

    private fun configData(list: List<ResponseCategoryItem>) {
        adapterCategory.setListener(null, itemListener)
        adapterCategory.addMoreItems(list)
    }

    private fun goToSearched(categoryItem: ResponseCategoryItem) {
        val direction: NavDirections =
            HomeFragmentDirections.actionHomeFragmentToResultListFragment("${categoryItem.id}$MODO_CATEGORY")
        findNavController().navigate(direction)
    }

}