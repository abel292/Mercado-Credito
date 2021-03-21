package com.abel.mercadoaea.util

data class Data<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Exception? = null
)

enum class Status { SUCCESSFUL, ERROR, EMPTY }

data class MainData(
    var responseType: StatusMain,
    var error: Exception? = null
)

enum class StatusMain { HOME, SHOW_RESULTS, LOADING, SEARCHING }

data class ViewerData(
    var responseType: StatusViewer,
    var error: Exception? = null
)

enum class StatusViewer { SHOW_ITEM, LOADING }