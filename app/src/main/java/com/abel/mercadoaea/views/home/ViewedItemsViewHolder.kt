package com.abel.mercadoaea.views.home

import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.databinding.ItemViewedItemsBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

class ViewedItemsViewHolder(private val binding: ItemViewedItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        itemEntity: ItemEntity?,
        onClickListener: OnClickItemRecyclerListener<ItemEntity>?
    ) {
        binding.item = itemEntity
        itemView.setOnClickListener {
            if (itemEntity != null) {
                onClickListener?.onClick(itemEntity)
            }
        }
    }
}