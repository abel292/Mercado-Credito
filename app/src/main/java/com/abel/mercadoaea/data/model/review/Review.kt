package com.abel.mercadoaea.data.model.review

data class Review(
    val buying_date: String?,
    val content: String?,
    val date_created: String?,
    val dislikes: Int,
    val forbidden_words: Int,
    val id: Int,
    val likes: Int,
    val rate: Int,
    val relevance: Int,
    val reviewable_object: ReviewableObject,
    val reviewer_id: Int,
    val status: String,
    val title: String,
    val valorization: Int
)