package com.abel.mercadoaea.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.api.ContsApi.Companion.MODO_CATEGORY
import com.abel.mercadoaea.data.model.category.ResponseCategory
import com.abel.mercadoaea.data.model.category.ResponseCategoryItem
import com.abel.mercadoaea.databinding.FragmentHomeBinding
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.toast
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {
    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CategoryAdapter
    private val itemListener =
        OnClickItemRecyclerListener<ResponseCategoryItem> { category ->
            showSearchedResult(category.id + MODO_CATEGORY)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        adapter = CategoryAdapter()
        adapter.setListener(null, itemListener)
        binding.adapter = adapter
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveDataCategory.observe(::getLifecycle, ::updateUI)
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
                data.data?.let {
                    adapter.loadList(it)
                }
            }
        }
    }

}