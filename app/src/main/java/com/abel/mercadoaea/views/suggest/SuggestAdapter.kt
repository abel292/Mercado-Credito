package com.abel.mercadoaea.views.suggest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.suggest.SuggestedQuery
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

//TODO ADAPTER
class SuggestAdapter(callback: SuggestCallback = SuggestCallback()) :
    ListAdapter<SuggestedQuery, SuggestViewHolder>(callback) {
    private lateinit var onClickItemRecyclerListener: OnClickItemRecyclerListener<SuggestedQuery>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        return SuggestViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_suggest,
                parent,
                false
            ), onClickItemRecyclerListener = onClickItemRecyclerListener
        )
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    fun setItemOnClick(onClickItemRecyclerListener: OnClickItemRecyclerListener<SuggestedQuery>) {
        this.onClickItemRecyclerListener = onClickItemRecyclerListener
    }
}

//TODO CALLBACK
class SuggestCallback :
    DiffUtil.ItemCallback<SuggestedQuery>() {

    override fun areItemsTheSame(oldItem: SuggestedQuery, newItem: SuggestedQuery): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SuggestedQuery, newItem: SuggestedQuery): Boolean {
        return oldItem.q == newItem.q &&
                oldItem.q == newItem.q
    }
}

