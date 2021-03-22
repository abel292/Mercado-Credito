package com.abel.mercadoaea.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.database.ItemEntity
import com.abel.mercadoaea.views.adapter.BaseAdapterLoadMore
import com.abel.mercadoaea.views.adapter.ProgressViewHolder

class ViewedItemsAdapter : BaseAdapterLoadMore<ItemEntity>() {
    override fun getItemViewType(position: Int): Int {
        return if (list[position] != null) viewItem else viewLoad
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == viewItem) {
            ViewedItemsViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_viewed_items,
                    parent,
                    false
                )
            )
        } else {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_loading_recycler, parent, false
            )
            ProgressViewHolder(v)
        }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewedItemsViewHolder) {
            val result = list[position]
            holder.bind(result, onClickListener)
        }
    }
}

