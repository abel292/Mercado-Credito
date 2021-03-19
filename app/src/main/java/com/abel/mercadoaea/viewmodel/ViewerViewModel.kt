package com.abel.mercadoaea.viewmodel

import androidx.lifecycle.MutableLiveData
import com.abel.mercadoaea.data.model.category.ResponseCategory
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewerViewModel(private val mercadoRepository: MercadoRepository) : BaseViewModel() {

    //region todo MUTABLES
    val liveDataDescription = MutableLiveData<Data<ResponseDescription>>()
    val liveDataReview = MutableLiveData<Data<ResponseReview>>()
    val liveDataItem = MutableLiveData<Data<ResponseItem>>()
    var viewerState: MutableLiveData<ViewerData> = MutableLiveData()

    //endregion

    fun getDescription(id: String) = launch {
        mercadoRepository.getDescriptionApi(idItem = id).collect {
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

    fun getItemComplete(id: String) = launch {
        mercadoRepository.getItemComplete(idItem = id).collect {
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