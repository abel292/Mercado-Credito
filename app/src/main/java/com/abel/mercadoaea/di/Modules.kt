package com.abel.mercadoaea.di

import com.abel.mercadoaea.BuildConfig
import com.abel.mercadoaea.data.api.MercadoApi
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.views.resultList.SearchedAdapter
import com.abel.mercadoaea.views.suggest.SuggestAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val moduleApp = module {
    single { MercadoRepository(get()) }
    single { provideMercadoApi() }
    single { SuggestAdapter() }
    single { SearchedAdapter() }
}

fun provideMercadoApi(): MercadoApi = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(getGson()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(MercadoApi::class.java)

fun getGson(): Gson = GsonBuilder()
    .setLenient()
    .create()

val moduleViewModels = module {
    viewModel { MainViewModel(get()) }
}
