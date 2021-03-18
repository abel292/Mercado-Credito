package com.abel.mercadoaea.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.suggest.SuggestedQuery
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.suggest.SuggestFragmentDirections
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<MainViewModel>()
    lateinit var navController: NavController
    private val Fragment.navController
        get() = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)

        configSearch()
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
