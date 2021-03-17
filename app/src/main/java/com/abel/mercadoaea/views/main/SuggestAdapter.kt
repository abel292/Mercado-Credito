package com.abel.mercadoaea.views.main

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
import com.abel.mercadoaea.data.model.search.Result

//TODO ADAPTER
class SearchAdapter(callback: SuggestCallback = SuggestCallback()) :
    ListAdapter<Result, SuggestViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(SuggestViewHolder.layoutResId, parent, false)
        return SuggestViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        val item = getItem(position) ?: return
        val hash = item.hashCode()
        val color = Color.rgb(Color.red(hash), Color.green(hash), Color.blue(hash))
        holder.bind(item, color)
    }
}

//TODO CALLBACK
class SuggestCallback :
    DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.title == newItem.title
    }
}

//TODO VIEW HOLDER
class SuggestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        const val layoutResId = R.layout.item_elf
    }

    val name: TextView = view.findViewById(R.id.item_title)
    val picture: ImageView = view.findViewById(R.id.item_picture)

    fun bind(item: Result, color: Int) {
        name.text = item.title
        picture.setBackgroundColor(color)
    }

}