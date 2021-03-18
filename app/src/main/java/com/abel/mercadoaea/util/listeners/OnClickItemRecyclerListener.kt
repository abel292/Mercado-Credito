package com.abel.mercadoaea.util

class OnClickItemRecyclerListener<T>(val clickListener: (T) -> Unit) {
    fun onClick(item: T) = clickListener(item)
}