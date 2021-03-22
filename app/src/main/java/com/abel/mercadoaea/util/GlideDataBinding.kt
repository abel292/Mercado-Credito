package com.abel.mercadoaea.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.abel.mercadoaea.R
import com.bumptech.glide.Glide

class GlideDataBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .override(300, 300)
                .centerCrop()
                .into(view)
        }
    }

}