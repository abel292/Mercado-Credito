package com.abel.mercadoaea.util

data class MainData(
    var responseType: StatusMain,
    var error: Exception? = null
)

enum class StatusMain { SEARCH_MODE, VIEW_RESULT_LIST, HOME, LOADING_GENERIC }