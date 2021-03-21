package com.abel.mercadoaea.views.base

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abel.mercadoaea.data.api.ContsApi
import com.abel.mercadoaea.data.model.suggest.SuggestedQuery
import com.abel.mercadoaea.views.home.HomeFragmentDirections
import com.abel.mercadoaea.views.resultList.ResultActivity
import com.abel.mercadoaea.views.suggest.SuggestFragmentDirections

abstract class BaseFragment : Fragment() {

    fun showSearchedResult(query: String) {
        val intent = Intent(requireActivity(), ResultActivity::class.java)
        intent.putExtra(ResultActivity.KEY_QUERY_EXTRA, query)
        startActivity(intent)
    }
}