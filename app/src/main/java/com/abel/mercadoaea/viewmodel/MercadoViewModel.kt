package com.abel.mercadoaea.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.model.search.ResponseSearch
import com.abel.mercadoaea.data.model.suggest.ResponseSuggest
import com.abel.mercadoaea.data.repositories.MercadoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MercadoViewModel(private val mercadoRepository: MercadoRepository) : ViewModel() {
    val resourceSuggestLive = MutableLiveData<ResourceResponse<ResponseSuggest>>()
    val resourceSearchLive = MutableLiveData<ResourceResponse<ResponseSearch>>()
    val resourceDescriptionLive = MutableLiveData<ResourceResponse<ResponseDescription>>()
    val resourceReview = MutableLiveData<ResourceResponse<ResponseReview>>()
    val resourceItem = MutableLiveData<ResourceResponse<ResponseItem>>()

    fun getSuggest(q: String) {
        viewModelScope.launch {
            mercadoRepository.getSuggestApi(q).collect {
                resourceSuggestLive.value = it
            }
        }
    }

    fun searchItems(q: String, offset: Int) {
        viewModelScope.launch {
            mercadoRepository.getListSearchedItems(q, offset).collect {
                resourceSearchLive.value = it
            }
        }
    }

    fun getDescription() {
        viewModelScope.launch {
            mercadoRepository.getDescriptionApi("MLA723647590").collect {
                resourceDescriptionLive.value = it
            }
        }
    }

    fun getReview() {
        viewModelScope.launch {
            mercadoRepository.getReviews("MLA723647586").collect {
                resourceReview.value = it
            }
        }
    }

    fun getItemComplete() {
        viewModelScope.launch {
            mercadoRepository.getItemComplete("MLA723647586").collect {
                resourceItem.value = it
            }
        }
    }
}