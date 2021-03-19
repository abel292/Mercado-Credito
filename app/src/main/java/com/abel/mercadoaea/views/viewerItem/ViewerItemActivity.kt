package com.abel.mercadoaea.views.viewerItem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.item.Picture
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.search.Result
import com.abel.mercadoaea.util.Data
import com.abel.mercadoaea.util.StatusViewer
import com.abel.mercadoaea.util.ViewerData
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.viewmodel.ViewerViewModel
import com.abel.mercadoaea.views.adapter.GalleryAdapter
import com.abel.mercadoaea.views.resultList.SearchedAdapter
import kotlinx.android.synthetic.main.activity_viewer_item.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewerItemActivity : AppCompatActivity() {

    private val viewModel by viewModel<ViewerViewModel>()
    private val galleryAdapter: GalleryAdapter by inject()
    private val itemPictureListener = OnClickItemRecyclerListener<Picture> { picture ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer_item)
        val argIdProduct = ViewerItemActivityArgs.fromBundle(bundle = intent.extras!!).argIdProduct

        viewModel.viewerState.observe(::getLifecycle, ::updateUI)
        viewModel.liveDataItem.observe(::getLifecycle, ::configUI)
        viewModel.getItemComplete(argIdProduct)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun configUI(dataItem: Data<ResponseItem>?) {
        textViewPriceViewer.text = dataItem?.data?.price.toString()
        textViewTitleViewer.text = dataItem?.data?.title.toString()
        dataItem?.data?.pictures?.let { configGallery(it) }
    }

    private fun updateUI(data: ViewerData) {
        when (data.responseType) {
            StatusViewer.SHOW_ITEM -> {
            }
            StatusViewer.LOADING -> {
            }
        }
    }

    private fun configGallery(pictures: List<Picture>) {
        galleryAdapter.setContext(this)
        galleryAdapter.setListener(itemPictureListener)
        galleryAdapter.addItems(pictures)
        galleryView.setSliderAdapter(galleryAdapter)
    }
}

