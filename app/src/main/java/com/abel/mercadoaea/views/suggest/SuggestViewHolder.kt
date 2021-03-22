package com.abel.mercadoaea.views.suggest

import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.data.model.suggest.SuggestedQuery
import com.abel.mercadoaea.databinding.ItemSuggestBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

class SuggestViewHolder(
    private val binding: ItemSuggestBinding,
    val onClickItemRecyclerListener: OnClickItemRecyclerListener<SuggestedQuery>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SuggestedQuery) {
        binding.suggest = item
        itemView.setOnClickListener {
            onClickItemRecyclerListener.onClick(item)
        }
    }
}