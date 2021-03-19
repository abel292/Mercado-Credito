package com.abel.mercadoaea.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.category.ResponseCategory
import com.abel.mercadoaea.data.model.category.ResponseCategoryItem
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapterCategory: CategoryAdapter by inject()
    private val itemListener =
        OnClickItemRecyclerListener<ResponseCategoryItem> { category -> /*goToViewerItem(result.id)*/ }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        recyclerViewCategory.adapter = adapterCategory
    }


}