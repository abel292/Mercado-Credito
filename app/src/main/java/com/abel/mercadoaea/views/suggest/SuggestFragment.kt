package com.abel.mercadoaea.views.suggest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.data.model.suggest.SuggestedQuery
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.util.toast
import com.abel.mercadoaea.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_suggest.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SuggestFragment : Fragment() {

    private val viewModel by sharedViewModel<MainViewModel>()
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
        viewModel.liveDataSuggest.observe(::getLifecycle, ::updateUISearch)
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

    private fun configViews() {
        context?.toast("estoy en suggest")

        recyclerViewSuggest.adapter = adapterSuggest
        adapterSuggest.setItemOnClick(OnClickItemRecyclerListener {
            goToSearched(it)
        })
    }

    private fun goToSearched(suggest: SuggestedQuery) {
        val direction: NavDirections =
            SuggestFragmentDirections.actionSearchFragmentToResultListFragment(suggest.q)
        findNavController().navigate(direction)
    }
}