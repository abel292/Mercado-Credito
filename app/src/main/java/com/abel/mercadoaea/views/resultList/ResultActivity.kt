package com.abel.mercadoaea.views.resultList

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.databinding.ActivityResultBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.listeners.OnLoadMoreListener
import com.abel.mercadoaea.viewmodel.MainViewModel
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.abel.mercadoaea.R
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.views.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultActivity : BaseActivity(), OnLoadMoreListener {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityResultBinding
    private lateinit var query: String
    private lateinit var adapter: ResultAdapter
    private val itemListener =
        OnClickItemRecyclerListener<Result> { result -> result.id?.let { show(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        adapter = ResultAdapter()
        adapter.setListener(this, itemListener)
        binding.adapter = adapter
        binding.recyclerViewSearched.layoutManager = GridLayoutManager(this, 2)
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
                binding.result = results.data
                results.data?.results?.let { adapter.addMoreItems(it) }
            }
        }
        if (binding.constraintLayout4.currentState == binding.constraintLayout4.startState) {
            binding.constraintLayout4.transitionToEnd()
        }
    }

    override fun onLoadMore(offset: Int) {
        adapter.modeLoading()
        viewModel.searchItems(query, offset = offset)
    }
}

