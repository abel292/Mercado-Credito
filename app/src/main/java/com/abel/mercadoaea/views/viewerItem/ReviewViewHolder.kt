package com.abel.mercadoaea.views.viewerItem

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.review.Review
import com.abel.mercadoaea.databinding.ItemReviewBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.util.toast

//todo Holder
class ReviewViewHolder(private val binding: ItemReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        review: Review?,
        onClickListener: OnClickItemRecyclerListener<Review>?
    ) {
        binding.review = review
        itemView.setOnClickListener {
            if (review != null) {
                binding.root.context.toast("Esta funcionalidad esta pendiente")
            }
        }
    }
}