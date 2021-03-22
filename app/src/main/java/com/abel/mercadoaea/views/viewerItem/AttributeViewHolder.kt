package com.abel.mercadoaea.views.viewerItem

import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.data.model.item.Attribute
import com.abel.mercadoaea.databinding.ItemAttributesBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

//todo Holder
class AttributeViewHolder(private val binding: ItemAttributesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        attribute: Attribute?,
        onClickListener: OnClickItemRecyclerListener<Attribute>?
    ) {
        binding.attribute = attribute
        itemView.setOnClickListener {
            if (attribute != null) {
                onClickListener?.onClick(attribute)
            }
        }
    }
}