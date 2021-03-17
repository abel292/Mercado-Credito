package com.abel.mercadoaea.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.toast
import com.abel.mercadoaea.viewmodel.MercadoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MercadoViewModel>()
    private val adapterSuggest: SuggestAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.mainState.observe(::getLifecycle, ::updateUISearch)
        configViews()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        this.toast("buscando: $query")
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            if (it.length > 1) viewModel.getSuggest(it) else adapterSuggest.submitList(listOf())
        }
        return false
    }

    private fun updateUISearch(suggest: Data<ResponseSuggest>) {
        when (suggest.responseType) {
            Status.ERROR -> {
            }
            Status.LOADING -> {
            }
            Status.SUCCESSFUL -> {
                suggest.data?.suggested_queries?.let { adapterSuggest.submitList(it) }
            }
        }
    }

    private fun configViews() {
        recyclerViewItems.adapter = adapterSuggest
        configSearch()
    }

    private fun configSearch() {
        searchItems.setOnQueryTextListener(this)
    }
}