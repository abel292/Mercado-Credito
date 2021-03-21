package com.abel.mercadoaea.views.suggest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.database.SuggestEntity
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.bumptech.glide.Glide

//todo Holder
class SuggestLocalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var textViewTitle: TextView = v.findViewById(R.id.item_title)
    fun bind(
        suggestEntity: SuggestEntity?,
        onClickListener: OnClickItemRecyclerListener<SuggestEntity>?
    ) {
        textViewTitle.text = suggestEntity?.suggest
        itemView.setOnClickListener {
            if (suggestEntity != null) {
                onClickListener?.onClick(suggestEntity)
            }
        }
    }
}