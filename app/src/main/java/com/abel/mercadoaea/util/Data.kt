package com.abel.mercadoaea.util

data class Data<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Exception? = null
)

enum class Status { SUCCESSFUL, ERROR, EMPTY }