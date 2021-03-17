package com.abel.mercadoaea.viewmodel

data class ResourceResponse<T>(
    var resourceObject: T?,
    var responseAction: Int?,
    var message: String = "",
    var loading: Boolean = false
) {
    companion object {
        const val CANCEL = 300
        const val ERROR = 400
        const val SUCCESS = 500
    }
}