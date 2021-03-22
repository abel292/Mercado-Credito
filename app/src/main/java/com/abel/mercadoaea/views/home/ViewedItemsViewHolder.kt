package com.abel.mercadoaea.views.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

class ViewedItemsViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var textViewTitle: TextView = v.findViewById(R.id.item_title)
    fun bind(
        result: ItemEntity?,
        onClickListener: OnClickItemRecyclerListener<ItemEntity>?
    ) {
        textViewTitle.text = result?.title
        itemView.setOnClickListener {
            if (result != null) {
                onClickListener?.onClick(result)
            }
        }
    }
}