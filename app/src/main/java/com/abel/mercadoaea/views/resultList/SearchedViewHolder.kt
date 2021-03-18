package com.abel.mercadoaea.views.resultList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.bumptech.glide.Glide

//todo Holder
class SearchedViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var textViewTitle: TextView = v.findViewById(R.id.item_title)
    var imageViewCharacter: ImageView = v.findViewById(R.id.item_picture)

    fun bind(
        result: Result?,
        onClickListener: OnClickItemRecyclerListener<Result>?
    ) {
        textViewTitle.text = result?.title
        Glide.with(itemView.context)
            .load(result?.thumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .override(200, 200)
            .centerCrop()
            .into(imageViewCharacter)

        itemView.setOnClickListener {
            if (result != null) {
                onClickListener?.onClick(result)
            }
        }
    }
}