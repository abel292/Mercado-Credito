package com.abel.mercadoaea.views.viewerItem

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.review.Review
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

//todo Holder
class ReviewViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var textViewTitle: TextView = v.findViewById(R.id.item_title)
    var textViewComment: TextView = v.findViewById(R.id.item_comment)
    var imageViewCharacter: ImageView = v.findViewById(R.id.item_picture)

    fun bind(
        review: Review?,
        onClickListener: OnClickItemRecyclerListener<Review>?
    ) {
        textViewTitle.text = review?.title
        textViewComment.text = review?.content

        itemView.setOnClickListener {
            if (review != null) {
                onClickListener?.onClick(review)
            }
        }
    }
}