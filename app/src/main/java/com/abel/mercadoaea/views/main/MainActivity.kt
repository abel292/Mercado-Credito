package com.abel.mercadoaea.views.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.api.ContsApi
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.base.BaseActivity
import com.abel.mercadoaea.views.resultList.ResultActivity
import com.abel.mercadoaea.views.suggest.SuggestFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MainViewModel>()
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            StatusMain.SEARCHING -> {
                goToSearch()
            }
            StatusMain.HOME -> {
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchItems.onActionViewCollapsed()
        query?.let { goToResultList(it) }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.getSuggest(it)
        }
        return false
    }

    private fun configSearch() {
        searchItems.setOnQueryTextListener(this)
        searchItems.onActionViewCollapsed()
    }

    private fun goToResultList(query: String) {
        searchItems.onActionViewCollapsed()
        showSearchedResult(query + ContsApi.MODO_QUERY)
    }

    private fun goToSearch() {
        when (NavHostFragment.findNavController(nav_host_fragment).currentDestination?.id) {
            R.id.searchFragment -> {
            }
            else -> navController.navigate(R.id.action_homeFragment_to_searchFragment)

        }
    }

    override fun onBackPressed() {
        try {
            when (NavHostFragment.findNavController(nav_host_fragment).currentDestination?.id) {
                R.id.homeFragment -> {
                    AlertDialog.Builder(this).setMessage("Salir").setPositiveButton(
                        "Ok"
                    ) { dialogInterface, i ->
                        finish()
                    }.show()
                }
                else -> {
                    super.onBackPressed()
                }
            }
        } catch (e: Exception) {
            onBackPressed()
        }
    }
}
