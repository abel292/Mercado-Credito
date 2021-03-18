package com.abel.mercadoaea.views.suggest

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.suggest.SuggestedQuery
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener

//TODO ADAPTER
class SuggestAdapter(callback: SuggestCallback = SuggestCallback()) :
    ListAdapter<SuggestedQuery, SuggestViewHolder>(callback) {
    private lateinit var onClickItemRecyclerListener: OnClickItemRecyclerListener<SuggestedQuery>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(SuggestViewHolder.layoutResId, parent, false)
        return SuggestViewHolder(view, onClickItemRecyclerListener = onClickItemRecyclerListener)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        val item = getItem(position) ?: return
        val hash = item.hashCode()
        val color = Color.rgb(Color.red(hash), Color.green(hash), Color.blue(hash))
        holder.bind(item, color)
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

//TODO VIEW HOLDER
class SuggestViewHolder(
    view: View,
    val onClickItemRecyclerListener: OnClickItemRecyclerListener<SuggestedQuery>
) : RecyclerView.ViewHolder(view) {

    val name: TextView = view.findViewById(R.id.item_title)
    val picture: ImageView = view.findViewById(R.id.item_picture)
    fun bind(item: SuggestedQuery, color: Int) {
        name.text = item.q
        picture.setBackgroundColor(color)
        itemView.setOnClickListener {
            onClickItemRecyclerListener.onClick(item)
        }
    }

    companion object {
        const val layoutResId = R.layout.item_suggest
    }

}