package com.abel.mercadoaea.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.item.Picture
import com.abel.mercadoaea.util.isValidGlideContext
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class GalleryAdapter() :
    SliderViewAdapter<GalleryAdapter.SliderAdapterVH>() {
    private var mSliderItems: ArrayList<Picture> = ArrayList()
    private lateinit var onClickListenerPhoto: OnClickItemRecyclerListener<List<Picture>>
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun setListener(onClickListenerPhoto: OnClickItemRecyclerListener<List<Picture>>) {
        this.onClickListenerPhoto = onClickListenerPhoto
    }

    fun addItems(sliderItem: List<Picture>) {
        mSliderItems.addAll(sliderItem)
        notifyDataSetChanged()
    }

    fun getItems():ArrayList<String>{
        val photos= ArrayList<String>()
        mSliderItems.forEach {
            photos.add(it.url)
        }
        return photos
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem = mSliderItems[position]
        if (context.isValidGlideContext()) {
            Glide.with(context)
                .load(sliderItem.url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .override(500, 500)
                .centerCrop()
                .into(viewHolder.imageViewBackground)
        }

        viewHolder.imageViewBackground.setOnClickListener {
            onClickListenerPhoto.onClickPosition(mSliderItems, position)
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