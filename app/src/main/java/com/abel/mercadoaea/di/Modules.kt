package com.abel.mercadoaea.di

import com.abel.mercadoaea.views.main.MainActivity
import org.koin.dsl.module


val moduleApp = module {
    single { MainActivity() }
}
val moduleViewModels = module {
    //viewModel { SessionViewModel() }
}