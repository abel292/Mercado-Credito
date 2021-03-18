package com.abel.mercadoaea.views.suggest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.toast
import com.abel.mercadoaea.viewmodel.MercadoViewModel
import com.abel.mercadoaea.views.main.SuggestAdapter
import kotlinx.android.synthetic.main.fragment_suggest.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuggestFragment : Fragment() {

    private val viewModel by sharedViewModel<MercadoViewModel>()
    private val adapterSuggest: SuggestAdapter by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_suggest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configViews()
        viewModel.mainState.observe(::getLifecycle, ::updateUISearch)
    }

    private fun updateUISearch(suggest: Data<ResponseSuggest>) {
        when (suggest.responseType) {
            Status.ERROR -> {
            }
            Status.LOADING -> {
            }
            Status.EMPTY -> {
                adapterSuggest.submitList(listOf())
            }
            Status.SUCCESSFUL -> {
                suggest.data?.suggested_queries?.let { adapterSuggest.submitList(it) }
            }
        }
    }

    private fun configViews() {
        recyclerViewSuggest.adapter = adapterSuggest
    }
}