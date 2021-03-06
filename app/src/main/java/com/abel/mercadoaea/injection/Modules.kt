package com.abel.mercadoaea.injection

import androidx.room.Room
import com.abel.mercadoaea.BuildConfig
import com.abel.mercadoaea.data.api.MercadoApi
import com.abel.mercadoaea.data.database.AppDatabase
import com.abel.mercadoaea.data.repositories.DataBaseRespository
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.viewmodel.MainViewModel
import com.abel.mercadoaea.viewmodel.ViewerViewModel
import com.abel.mercadoaea.views.adapter.GalleryAdapter
import com.abel.mercadoaea.views.suggest.SuggestAdapter
import com.abel.mercadoaea.views.suggest.SuggestLocalAdapter
import com.abel.mercadoaea.views.viewerItem.ReviewAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val moduleApp = module {
    single { MercadoRepository(get(), get()) }
    single { DataBaseRespository(get()) }
    single { provideMercadoApi() }
    single { SuggestAdapter() }
    single { SuggestLocalAdapter() }
    factory { GalleryAdapter() }
    factory { ReviewAdapter() }
}

val databaseModule = module {
    single {
        val db: AppDatabase = get()
        db.suggestDao()
    }

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "mercado_libre")
            .fallbackToDestructiveMigration()
            .build()
    }
}

val moduleViewModels = module {
    viewModel { MainViewModel(get()) }
    viewModel { ViewerViewModel(get()) }
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