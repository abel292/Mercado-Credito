package com.abel.mercadoaea.views.resultList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.databinding.ActivityResultBinding
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.listeners.OnLoadMoreListener
import com.abel.mercadoaea.viewmodel.MainViewModel
import androidx.databinding.DataBindingUtil
import com.abel.mercadoaea.R
import com.abel.mercadoaea.views.viewerItem.ViewerItemActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultActivity : AppCompatActivity(), OnLoadMoreListener {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityResultBinding
    private lateinit var query: String
    private lateinit var adapter: ResultAdapter
    private val itemListener =
        OnClickItemRecyclerListener<Result> { result -> show(result.id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        adapter = ResultAdapter()
        adapter.setListener(this, itemListener)
        binding.adapter = adapter
        binding.viewModel = viewModel

        viewModel.liveDataSearch.observe(::getLifecycle, ::updateUISearch)
        query = ResultActivityArgs.fromBundle(bundle = intent.extras!!).argQuery
        viewModel.searchItems(query)
    }

    private fun updateUISearch(results: Data<ResponseSearch>) {
        when (results.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
            }
            Status.SUCCESSFUL -> {
                results.data?.results?.let {
                    adapter.addMoreItems(it)
                }
            }
        }
    }

    override fun onLoadMore(offset: Int) {
        adapter.modeLoading()
        viewModel.searchItems(query, offset = offset)
    }

    private fun show(idItem: String) {
        val intent = Intent(this, ViewerItemActivity::class.java)
        intent.putExtra(KEY_ID_ITEM, idItem)
        startActivity(intent)
    }

    companion object {
        const val KEY_QUERY_EXTRA = "argQuery"
        const val KEY_ID_ITEM = "argIdProduct"
    }
}

