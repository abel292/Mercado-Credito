package com.abel.mercadoaea.views.adapter

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.abel.mercadoaea.R


class ProgressViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var progressBar: ProgressBar = v.findViewById<View>(R.id.progressBar) as ProgressBar

}