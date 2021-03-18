package com.abel.mercadoaea.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)

        configSearch()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        toast("buscando: $query")
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
        searchItems.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                navController.navigate(R.id.actionSearchFragment)
            } else {
                navController.navigate(R.id.actionHomeFragment)

            }
        }
    }

}