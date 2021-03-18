package com.abel.mercadoaea.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.item.Picture
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class GalleryAdapter :
    SliderViewAdapter<GalleryAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<Picture> = ArrayList()
    private lateinit var onClickListenerPhoto: OnClickItemRecyclerListener<Picture>

    fun setListener(onClickListenerPhoto: OnClickItemRecyclerListener<Picture>) {
        this.onClickListenerPhoto = onClickListenerPhoto
    }

    fun addItems(sliderItem: List<Picture>) {
        mSliderItems.addAll(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem = mSliderItems[position]

        Glide.with(viewHolder.imageViewBackground.context)
            .load(sliderItem.url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .override(500, 500)
            .centerCrop()
            .into(viewHolder.imageViewBackground)

        viewHolder.imageViewBackground.setOnClickListener {
            onClickListenerPhoto.onClick(sliderItem)
        }
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class SliderAdapterVH(
        itemView: View,
    ) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.imageViewSlide)
    }
}