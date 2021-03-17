package com.abel.mercadoaea.util

sealed class ResultResource<out T : Any> {
    class Success<out T : Any>(val data: T) : ResultResource<T>()
    class Failure(val exception: Exception = Exception()) : ResultResource<Nothing>()
}