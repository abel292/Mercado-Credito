package com.abel.mercadoaea.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.data.repositories.MercadoRepository
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.ResultResource
import com.abel.mercadoaea.util.Status
import com.abel.mercadoaea.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewerViewModel(private val mercadoRepository: MercadoRepository) : BaseViewModel() {

    //region todo MUTABLES
    val liveDataReview = MutableLiveData<Data<ResponseReview>>()
    val liveDataItem = MutableLiveData<Data<ResponseItem>>()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    //endregion

    fun hideLoading() = launch {
        loadingVisibility.value = View.GONE
    }


    fun getReview(id: String) = launch {
        mercadoRepository.getReviews(idItem = id).collect {
            delay(3000) //para apreciar mas el loading
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
        mercadoRepository.getItemComplete(idItem = id).collect { resource ->
            when (resource) {
                is ResultResource.Failure -> {
                    liveDataItem.value =
                        Data(responseType = Status.ERROR, error = resource.exception)
                }
                is ResultResource.Success -> {

                    //buscamos la descripcion
                    withContext(Dispatchers.Default) {
                        mercadoRepository.getDescriptionApi(idItem = id).collect {
                            when (it) {
                                is ResultResource.Failure -> {
                                    resource.data.descriptionsCompleted =
                                        "Producto sin descripcion"
                                }
                                is ResultResource.Success -> {
                                    resource.data.descriptionsCompleted =
                                        it.data.plain_text.toString()
                                }
                            }
                        }
                    }

                    delay(2000)
                    liveDataItem.value =
                        Data(responseType = Status.SUCCESSFUL, data = resource.data)
                }
            }
        }
    }

}