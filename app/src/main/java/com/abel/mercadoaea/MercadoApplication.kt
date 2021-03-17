package com.abel.mercadoaea

import android.app.Application
import com.abel.mercadoaea.di.moduleApp
import com.abel.mercadoaea.di.moduleViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MercadoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MercadoApplication)
            modules(moduleApp, moduleViewModels)
        }
    }
}