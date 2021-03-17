package com.abel.mercadoaea.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abel.mercadoaea.R
import com.abel.mercadoaea.viewmodel.MercadoViewModel
import com.abel.mercadoaea.viewmodel.ResourceResponse.Companion.ERROR
import com.abel.mercadoaea.viewmodel.ResourceResponse.Companion.SUCCESS
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MercadoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObservables()

        button.setOnClickListener {
            val txt = editTextTextPersonName.text?.toString() ?: ""
            //viewModel.getSuggest(txt)
            viewModel.searchItems(txt, 0)
        }
    }

    private fun initObservables() {
        viewModel.resourceSearchLive.observe(this, {
            when (it.responseAction) {
                SUCCESS -> {
                    it.resourceObject?.results?.forEach {
                        Log.d("busqueda", it.title)
                    }

                }
                ERROR -> {
                    Log.e("busqueda", it.resourceObject.toString())
                }
            }
        })
    }
}