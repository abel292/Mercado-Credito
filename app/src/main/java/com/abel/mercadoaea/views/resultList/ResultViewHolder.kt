package com.abel.mercadoaea.views.resultList

import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.databinding.ItemResultsBinding
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

//todo Holder
class ResultViewHolder(private val binding: ItemResultsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        result: Result?,
        onClickListener: OnClickItemRecyclerListener<Result>?
    ) {
        binding.result = result
        itemView.setOnClickListener {
            if (result != null) {
                onClickListener?.onClick(result)
            }
        }
    }
}