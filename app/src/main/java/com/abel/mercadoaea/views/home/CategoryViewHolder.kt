package com.abel.mercadoaea.views.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.category.ResponseCategoryItem
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

class CategoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var textViewTitle: TextView = v.findViewById(R.id.item_title_category)
    fun bind(
        result: ResponseCategoryItem?,
        onClickListener: OnClickItemRecyclerListener<ResponseCategoryItem>?
    ) {
        textViewTitle.text = result?.name
        itemView.setOnClickListener {
            if (result != null) {
                onClickListener?.onClick(result)
            }
        }
    }
}