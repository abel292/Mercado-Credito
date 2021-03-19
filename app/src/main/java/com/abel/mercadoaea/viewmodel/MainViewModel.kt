package com.abel.mercadoaea.viewmodel

import androidx.lifecycle.MutableLiveData
import com.abel.mercadoaea.data.api.ContsApi.Companion.MODO_CATEGORY
import com.abel.mercadoaea.data.api.ContsApi.Companion.MODO_QUERY
import com.abel.mercadoaea.data.model.category.ResponseCategory
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val mercadoRepository: MercadoRepository) : BaseViewModel() {

    //region todo MUTABLES
    val liveDataSearch = MutableLiveData<Data<ResponseSearch>>()
    var liveDataSuggest: MutableLiveData<Data<ResponseSuggest>> = MutableLiveData()
    var mainState: MutableLiveData<MainData> = MutableLiveData()
    val liveDataCategory = MutableLiveData<Data<ResponseCategory>>()

    //endregion

    //region todo MAIN
    fun hideSearchToolbar() {
        mainState.value = MainData(StatusMain.SHOW_RESULTS)
    }
    //endregion

    //region todo DATA

    fun getCategory() = launch {
        mercadoRepository.getCategoryApi().collect {
            when (it) {
                is ResultResource.Failure -> {
                    liveDataCategory.value =
                        Data(responseType = Status.ERROR, error = it.exception)
                }
                is ResultResource.Success -> {
                    liveDataCategory.value =
                        Data(responseType = Status.SUCCESSFUL, data = it.data)
                }
            }
        }
    }

    fun getSuggest(q: String) = launch {
        if (q.isEmpty()) {
            liveDataSuggest.value =
                Data(responseType = Status.EMPTY, data = null)
        } else {
            mercadoRepository.getSuggestApi(q).collect {
                when (it) {
                    is ResultResource.Failure -> {
                        liveDataSuggest.value =
                            Data(responseType = Status.ERROR, error = it.exception)
                    }
                    is ResultResource.Success -> {
                        liveDataSuggest.value =
                            Data(responseType = Status.SUCCESSFUL, data = it.data)
                    }
                }
            }
        }
    }

    fun searchItems(query: String, offset: Int = 0) = launch {
        hideSearchToolbar()
        when {
            query.contains(MODO_QUERY) -> {
                val q = query.replace(MODO_QUERY, "")
                mercadoRepository.getListSearchedItems(q, offset).collect {
                    when (it) {
                        is ResultResource.Failure -> {
                            liveDataSearch.value =
                                Data(responseType = Status.ERROR, error = it.exception)
                        }
                        is ResultResource.Success -> {
                            liveDataSearch.value =
                                Data(responseType = Status.SUCCESSFUL, data = it.data)
                        }
                    }
                }
            }
            query.contains(MODO_CATEGORY) -> {
                val q = query.replace(MODO_CATEGORY, "")
                mercadoRepository.getListSearchedCategory(q, offset).collect {
                    when (it) {
                        is ResultResource.Failure -> {
                            liveDataSearch.value =
                                Data(responseType = Status.ERROR, error = it.exception)
                        }
                        is ResultResource.Success -> {
                            liveDataSearch.value =
                                Data(responseType = Status.SUCCESSFUL, data = it.data)
                        }
                    }
                }
            }
            else -> {
                liveDataSearch.value =
                    Data(
                        responseType = Status.ERROR,
                        error = Exception("No se aplico ningun filtro")
                    )
            }
        }


    }
    //endregion
}