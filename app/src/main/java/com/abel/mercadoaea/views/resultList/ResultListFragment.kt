package com.abel.mercadoaea.views.resultList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.listeners.OnLoadMoreListener
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.toast
import com.abel.mercadoaea.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_result_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ResultListFragment : Fragment(), OnLoadMoreListener {
    private val viewModel by sharedViewModel<MainViewModel>()
    private val adapterSearched: SearchedAdapter by inject()
    private lateinit var query: String
    private val Fragment.navController
        get() = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )


    private val itemListener = OnClickItemRecyclerListener<Result> { result ->
        Log.e(
            "ACTION",
            "ME RE FUI AL OTRO ACTIVITY"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        query = ResultListFragmentArgs.fromBundle(args).argQuery
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configViews()
        viewModel.liveDataSearch.observe(::getLifecycle, ::updateUISearch)
        viewModel.searchItems(query)
    }

    override fun onLoadMore(offset: Int) {
        viewModel.searchItems(query, offset = offset)
    }

    private fun updateUISearch(results: Data<ResponseSearch>) {
        when (results.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
            }
            Status.SUCCESSFUL -> {
                results.data?.results?.let {
                    adapterSearched.addMoreItems(it)
                }
            }
        }
    }

    private fun configViews() {
        context?.toast("estoy en resultList")
        recyclerViewSearched.adapter = adapterSearched
        configAdapter()
    }

    private fun configAdapter() {
        adapterSearched.setOnLoadMoreListener(this, itemListener)
    }

}