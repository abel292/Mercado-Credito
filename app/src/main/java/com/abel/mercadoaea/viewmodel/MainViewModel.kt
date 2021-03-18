package com.abel.mercadoaea.viewmodel

import androidx.lifecycle.MutableLiveData
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val mercadoRepository: MercadoRepository) : BaseViewModel() {
    val liveDataSearch = MutableLiveData<Data<ResponseSearch>>()
    val liveDataDescription = MutableLiveData<Data<ResponseDescription>>()
    val liveDataReview = MutableLiveData<Data<ResponseReview>>()
    val liveDataItem = MutableLiveData<Data<ResponseItem>>()
    var liveDataSuggest: MutableLiveData<Data<ResponseSuggest>> = MutableLiveData()

    var mainState: MutableLiveData<MainData> = MutableLiveData()

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
        mercadoRepository.getListSearchedItems(query, offset).collect {
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

    fun getDescription() = launch {
        mercadoRepository.getDescriptionApi("MLA723647590").collect {
            when (it) {
                is ResultResource.Failure -> {
                    liveDataDescription.value =
                        Data(responseType = Status.ERROR, error = it.exception)
                }
                is ResultResource.Success -> {
                    liveDataDescription.value =
                        Data(responseType = Status.SUCCESSFUL, data = it.data)
                }
            }
        }
    }

    fun getReview() = launch {
        mercadoRepository.getReviews("MLA723647586").collect {
            when (it) {
                is ResultResource.Failure -> {
                    liveDataReview.value =
                        Data(responseType = Status.ERROR, error = it.exception)
                }
                is ResultResource.Success -> {
                    liveDataReview.value =
                        Data(responseType = Status.SUCCESSFUL, data = it.data)
                }
            }
        }
    }

    fun getItemComplete() = launch {
        mercadoRepository.getItemComplete("MLA723647586").collect {
            when (it) {
                is ResultResource.Failure -> {
                    liveDataItem.value =
                        Data(responseType = Status.ERROR, error = it.exception)
                }
                is ResultResource.Success -> {
                    liveDataItem.value =
                        Data(responseType = Status.SUCCESSFUL, data = it.data)
                }
            }
        }
    }
}