package com.abel.mercadoaea.data.model.review

data class ResponseReview(
    val helpful_reviews: HelpfulReviews?,
    val paging: Paging?,
    val rating_average: Float?,
    val rating_levels: RatingLevels?,
    val reviews: List<Review>?
)