package com.abel.mercadoaea.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.ResultResource
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MercadoViewModel(private val mercadoRepository: MercadoRepository) : BaseViewModel() {
    val resourceSuggestLive = MutableLiveData<ResourceResponse<ResponseSuggest>>()
    val resourceSearchLive = MutableLiveData<ResourceResponse<ResponseSearch>>()
    val resourceDescriptionLive = MutableLiveData<ResourceResponse<ResponseDescription>>()
    val resourceReview = MutableLiveData<ResourceResponse<ResponseReview>>()
    val resourceItem = MutableLiveData<ResourceResponse<ResponseItem>>()

    private var mutableMainState: MutableLiveData<Data<ResponseSuggest>> = MutableLiveData()
    val mainState: LiveData<Data<ResponseSuggest>>
        get() {
            return mutableMainState
        }

    fun getSuggest(q: String) = launch {
        mutableMainState.value = Data(responseType = Status.LOADING)
        mercadoRepository.getSuggestApi(q).collect {
            when (it) {
                is ResultResource.Failure -> {
                    mutableMainState.value =
                        Data(responseType = Status.ERROR, error = it.exception)
                }
                is ResultResource.Success -> {
                    mutableMainState.value =
                        Data(responseType = Status.SUCCESSFUL, data = it.data)
                }
            }
        }


    }

    fun searchItems(q: String, offset: Int) = launch {
        mercadoRepository.getListSearchedItems(q, offset).collect {
            resourceSearchLive.value = it
        }
    }

    fun getDescription() = launch {
        mercadoRepository.getDescriptionApi("MLA723647590").collect {
            resourceDescriptionLive.value = it
        }
    }

    fun getReview() = launch {
        mercadoRepository.getReviews("MLA723647586").collect {
            resourceReview.value = it
        }
    }

    fun getItemComplete() = launch {
        mercadoRepository.getItemComplete("MLA723647586").collect {
            resourceItem.value = it
        }
    }
}