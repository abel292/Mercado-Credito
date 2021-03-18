package com.abel.mercadoaea.views.adapter

import android.content.Context
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

    fun renewItems(sliderItems: MutableList<Picture>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun setListener(onClickListenerPhoto: OnClickItemRecyclerListener<Picture>) {
        this.onClickListenerPhoto = onClickListenerPhoto
    }

    fun getItems(): ArrayList<Picture> {
        val photos = ArrayList<Picture>()
        photos.addAll(mSliderItems)
        return photos
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

        Glide.with(viewHolder.itemView.context)
            .load(sliderItem)
            .fitCenter()
            .into(viewHolder.imageViewBackground)

        viewHolder.imageViewBackground.setOnClickListener {
            onClickListenerPhoto.onClick(sliderItem)
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    inner class SliderAdapterVH(
        itemView: View,
    ) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.imageViewSlide)
    }
}