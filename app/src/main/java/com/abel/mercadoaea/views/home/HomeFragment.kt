package com.abel.mercadoaea.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.api.ContsApi.Companion.MODO_CATEGORY
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.data.model.category.ResponseCategory
import com.abel.mercadoaea.data.model.category.ResponseCategoryItem
import com.abel.mercadoaea.databinding.FragmentHomeBinding
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {
    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CategoryAdapter
    private lateinit var adapterViewedItem: ViewedItemsAdapter

    private val itemListener =
        OnClickItemRecyclerListener<ResponseCategoryItem> { category ->
            showSearchedResult(category.id + MODO_CATEGORY)
        }
    private val itemListenerViewedItems =
        OnClickItemRecyclerListener<ItemEntity> { item ->
            show(item.id)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        adapter = CategoryAdapter()
        adapterViewedItem = ViewedItemsAdapter()
        adapter.setListener(null, itemListener)
        adapterViewedItem.setListener(null, itemListenerViewedItems)
        binding.adapter = adapter
        binding.adapterViewedItem = adapterViewedItem
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewLastProductsSeen.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveDataCategory.observe(::getLifecycle, ::updateUI)
        viewModel.liveDataLastViewedSeed.observe(::getLifecycle, ::updateRecyclerViewed)
        viewModel.getCategory()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLastItemViewed()
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

    private fun updateRecyclerViewed(data: Data<List<ItemEntity>>) {
        when (data.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
            }
            Status.SUCCESSFUL -> {
                data.data?.let {
                    adapterViewedItem.loadList(it)
                }
            }
        }
    }

}