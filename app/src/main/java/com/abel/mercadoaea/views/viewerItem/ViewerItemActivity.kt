package com.abel.mercadoaea.views.viewerItem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.abel.mercadoaea.R
import com.abel.mercadoaea.data.model.description.ResponseDescription
import com.abel.mercadoaea.data.model.item.Picture
import com.abel.mercadoaea.data.model.item.ResponseItem
import com.abel.mercadoaea.data.model.review.ResponseReview
import com.abel.mercadoaea.databinding.ActivityViewerItemBinding
import com.abel.mercadoaea.util.*
import com.abel.mercadoaea.util.listeners.OnClickItemRecyclerListener
import com.abel.mercadoaea.viewmodel.ViewerViewModel
import com.abel.mercadoaea.views.adapter.GalleryAdapter
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import kotlinx.android.synthetic.main.activity_viewer_item.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewerItemActivity : AppCompatActivity() {

    private val viewModel by viewModel<ViewerViewModel>()
    private val galleryAdapter: GalleryAdapter by inject()
    private lateinit var reviewsAdapter: ReviewAdapter
    private lateinit var atributtesAdapter: AtributtesAdapter
    private lateinit var binding: ActivityViewerItemBinding
    private val itemPictureListener = OnClickItemRecyclerListener { pictures: List<Picture> ->
        showFullScreen(galleryAdapter.getItems())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewer_item)
        binding.recyclerViewReviews.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewAtributes.layoutManager = LinearLayoutManager(this)
        binding.textViewDescripcion.setOnClickListener {
            binding.textViewDescripcion.toggle()
        }
        reviewsAdapter = ReviewAdapter()
        atributtesAdapter = AtributtesAdapter()
        binding.adapteReviews = reviewsAdapter
        binding.adapterAttribute = atributtesAdapter
        val argIdProduct = ViewerItemActivityArgs.fromBundle(bundle = intent.extras!!).argIdProduct

        viewModel.viewerState.observe(::getLifecycle, ::updateUI)
        viewModel.liveDataItem.observe(::getLifecycle, ::configUI)
        viewModel.liveDataReview.observe(::getLifecycle, ::configReviews)
        viewModel.liveDataDescription.observe(::getLifecycle, ::configDescription)
        viewModel.getItemComplete(argIdProduct)
        viewModel.getReview(argIdProduct)
        viewModel.getDescription(argIdProduct)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun configUI(dataItem: Data<ResponseItem>?) {
        dataItem?.data?.pictures?.let { configGallery(it) }
        dataItem?.data?.attributes?.let { atributtesAdapter.loadList(it) }
        dataItem?.data?.price?.let { binding.textViewPriceViewer.text = it.toString() }
        dataItem?.data?.title?.let { binding.textViewTitleViewer.text = it }

    }

    private fun configDescription(dataItem: Data<ResponseDescription>) {
        when (dataItem.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
            }
            Status.SUCCESSFUL -> {
                dataItem.data?.let {
                    binding.textViewDescripcion.text = if (it.plain_text.isEmpty()) {
                        getString(R.string.with_out_description)
                    } else {
                        it.plain_text
                    }
                }
            }
        }
    }

    private fun configReviews(dataItem: Data<ResponseReview>) {
        when (dataItem.responseType) {
            Status.ERROR -> {
            }
            Status.EMPTY -> {
            }
            Status.SUCCESSFUL -> {
                dataItem.data?.reviews?.let {
                    reviewsAdapter.loadList(it)
                    binding.textViewPromedioReview.text =
                        (dataItem.data?.rating_average ?: 0.0f).toString()

                    binding.ratingPromedio.rating = dataItem.data?.rating_average ?: 0.0f
                }
            }
        }
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

    private fun showFullScreen(uriString: ArrayList<String>) {
        val fullImageIntent = Intent(this, FullScreenImageViewActivity::class.java)
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString)
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, 0)
        startActivity(fullImageIntent)
    }

}

