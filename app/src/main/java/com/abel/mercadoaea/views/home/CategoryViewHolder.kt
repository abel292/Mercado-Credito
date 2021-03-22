package com.abel.mercadoaea.views.home

import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.data.model.category.ResponseCategoryItem
import com.abel.mercadoaea.databinding.ItemCategoryBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        categoryItem: ResponseCategoryItem?,
        onClickListener: OnClickItemRecyclerListener<ResponseCategoryItem>?
    ) {
        binding.category = categoryItem
        itemView.setOnClickListener {
            if (categoryItem != null) {
                onClickListener?.onClick(categoryItem)
            }
        }
    }
}