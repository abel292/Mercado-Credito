package com.abel.mercadoaea.views.suggest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.database.SuggestEntity
import com.abel.mercadoaea.databinding.ItemSuggestLocalBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.bumptech.glide.Glide

//todo Holder
class SuggestLocalViewHolder(private val binding: ItemSuggestLocalBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        suggestEntity: SuggestEntity?,
        onClickListener: OnClickItemRecyclerListener<SuggestEntity>?
    ) {
        suggestEntity?.let {
            binding.suggest = it
        }
        itemView.setOnClickListener {
            if (suggestEntity != null) {
                onClickListener?.onClick(suggestEntity)
            }
        }
    }
}