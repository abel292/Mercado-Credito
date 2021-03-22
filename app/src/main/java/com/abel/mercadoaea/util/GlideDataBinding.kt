package com.abel.mercadoaea.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.search.Price
import com.bumptech.glide.Glide

class GlideDataBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .override(300, 300)
                .centerCrop()
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("parseString")
        fun parseString(view: TextView, price: Double) {
            val value = price.toInt().toString()
            view.text = value
        }
    }


}

