package com.abel.mercadoaea.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.navigation.*
import com.abel.mercadoaea.R
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.suggest.SuggestFragmentDirections
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        configSearch()
    }

    private fun updateUI(results: MainData) {
        when (results.responseType) {
            StatusMain.SHOW_RESULTS -> {
                searchItems.onActionViewCollapsed()
            }
            StatusMain.LOADING -> {
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchItems.onActionViewCollapsed()
        query?.let { goToResultList(it) }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.getSuggest(it) }
        return false
    }

    private fun configSearch() {
        searchItems.setOnQueryTextListener(this)
        searchItems.onActionViewCollapsed()
        searchItems.setOnSearchClickListener {
            goToSearch()
        }
    }

    private fun goToResultList(query: String) {
        searchItems.onActionViewCollapsed()
        val direction: NavDirections =
            SuggestFragmentDirections.actionSearchFragmentToResultListFragment(query)
        navController.navigate(direction)
    }

    private fun goToSearch() {
        navController.navigate(R.id.action_homeFragment_to_searchFragment)
    }

}
