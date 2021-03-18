package com.abel.mercadoaea.views.resultList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.views.adapter.BaseAdapterLoadMore
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.views.adapter.ProgressViewHolder

class SearchedAdapter() : BaseAdapterLoadMore<Result>() {

    override fun getItemViewType(position: Int): Int {
        return if (list[position] != null) viewItem else viewLoad
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == viewItem) {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_suggest, parent, false
            )
            SearchedViewHolder(v)
        } else {
            val v: View = LayoutInflater.from(parent.context).inflate(
                R.layout.item_loading_recycler, parent, false
            )
            ProgressViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchedViewHolder) {
            val result = list[position]
            holder.bind(result, onClickListener)
        }
    }

}