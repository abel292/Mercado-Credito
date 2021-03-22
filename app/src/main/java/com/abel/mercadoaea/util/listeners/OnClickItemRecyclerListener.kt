package com.abel.mercadoaea.util.listeners

class OnClickItemRecyclerListener<T>(val clickListener: (T) -> Unit) {
    fun onClick(item: T) = clickListener(item)
    fun onClickPosition(item: T, position: Int) = clickListener(item)
}