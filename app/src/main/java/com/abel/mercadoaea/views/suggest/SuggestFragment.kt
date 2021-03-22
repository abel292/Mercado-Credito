package com.abel.mercadoaea.views.suggest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.api.ContsApi
import com.abel.mercadoaea.data.database.SuggestEntity
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.databinding.FragmentSuggestBinding
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.StatusMain
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SuggestFragment : BaseFragment() {

    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapterSuggest: SuggestAdapter by inject()
    private val adapterSuggestLocal: SuggestLocalAdapter by inject()
    private lateinit var binding: FragmentSuggestBinding
    private val itemListener =
        OnClickItemRecyclerListener<SuggestEntity> { suggestEntity ->
            showSearchedResult(
                suggestEntity.suggest + ContsApi.MODO_QUERY
            )
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_suggest, container, false)
        binding.recyclerViewSuggestLocal.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewSuggest.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterSuggest.setItemOnClick(OnClickItemRecyclerListener { showSearchedResult(it.q + ContsApi.MODO_QUERY) })
        adapterSuggestLocal.setListener(null, itemListener)
        binding.adapterSuggest = adapterSuggest
        binding.adapterSuggestLocal = adapterSuggestLocal
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataSuggest.observe(::getLifecycle, ::updateUISearch)
        viewModel.liveDataLastSearched.observe(::getLifecycle, ::updateListLastSearch)
    }

    override fun onResume() {
        super.onResume()
        viewModel.changeStatusMain(StatusMain.SEARCHING)
    }

    private fun updateUISearch(suggest: Data<ResponseSuggest>) {
        when (suggest.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
                adapterSuggest.submitList(listOf())
            }
            Status.SUCCESSFUL -> {
                suggest.data?.suggested_queries?.let { adapterSuggest.submitList(it) }
            }
        }
    }

    private fun updateListLastSearch(lastSearched: Data<List<SuggestEntity>>) {
        when (lastSearched.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
                adapterSuggestLocal.list = arrayListOf()
            }
            Status.SUCCESSFUL -> {
                lastSearched.data?.let { adapterSuggestLocal.loadList(it) }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.changeStatusMain(StatusMain.HOME)
    }

}