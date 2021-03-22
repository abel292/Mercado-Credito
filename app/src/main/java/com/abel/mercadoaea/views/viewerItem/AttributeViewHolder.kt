package com.abel.mercadoaea.views.viewerItem

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.item.Attribute
import com.abel.mercadoaea.databinding.ItemAttributesBinding
import com.abel.mercadoaea.util.generateColorRandom
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import java.util.*

//todo Holder
class AttributeViewHolder(private val binding: ItemAttributesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        attribute: Attribute?,
        onClickListener: OnClickItemRecyclerListener<Attribute>?
    ) {
        try {
            val color = if (position % 2 > 0) {
                R.color.tree
            } else {
                R.color.white
            }
            binding.root.setBackgroundResource(color)

        } catch (e: Exception) {
        }

        binding.attribute = attribute
        itemView.setOnClickListener {
            if (attribute != null) {
                onClickListener?.onClick(attribute)
            }
        }
    }
}