package com.abel.mercadoaea.data.model.review

data class ResponseReview(
    val attributes: List<Any>,
    val helpful_reviews: HelpfulReviews,
    val paging: Paging,
    val rating_average: Double,
    val rating_levels: RatingLevels,
    val reviews: List<Review>
)